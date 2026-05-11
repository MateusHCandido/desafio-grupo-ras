package com.grupo.ras.tarifa.service;

import com.grupo.ras.tarifa.controller.dto.calculo.CalculoTarifaResponse;
import com.grupo.ras.tarifa.controller.dto.calculo.DetalhamentoFaixaResponse;
import com.grupo.ras.tarifa.controller.dto.calculo.FaixaResponse;
import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.entity.FaixaConsumo;
import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.enums.CategoriaConsumidor;
import com.grupo.ras.tarifa.enums.StatusTabelaTarifaria;
import com.grupo.ras.tarifa.repository.provider.TabelaTarifariaRepositoryProvider;
import com.grupo.ras.tarifa.service.exceptions.CategoriaNaoEncontradaException;
import com.grupo.ras.tarifa.service.exceptions.FalhaDetalhamentoException;
import com.grupo.ras.tarifa.service.exceptions.SemRegistroTabelaException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculoService {

    private final TabelaTarifariaRepositoryProvider tabelaTarifariaRepository;

    public CalculoTarifaResponse calcular(CategoriaConsumidor categoriaConsumidor, Integer consumoTotal) {
        try{
            log.info("Iniciando cálculo da tarifa");
            TabelaTarifaria tabelaVigente = tabelaTarifariaRepository
                    .findByStatus(StatusTabelaTarifaria.ATIVA)
                    .orElseThrow(SemRegistroTabelaException::new);

            CategoriaTarifaria categoriaTarifaria = tabelaVigente.getCategorias()
                    .stream()
                    .filter(c -> c.getCategoriaConsumidor().equals(categoriaConsumidor))
                    .findFirst()
                    .orElseThrow(CategoriaNaoEncontradaException::new);

            List<FaixaConsumo> faixas = categoriaTarifaria.getFaixas()
                    .stream()
                    .sorted(Comparator.comparing(FaixaConsumo::getFaixaInicial))
                    .toList();

            BigDecimal valorTotal = BigDecimal.ZERO;

            List<DetalhamentoFaixaResponse> detalhamento = new ArrayList<>();

            int consumoRestante = consumoTotal;

            for (FaixaConsumo faixa : faixas) {

                if (consumoRestante <= 0) {
                    break;
                }

                int inicio = faixa.getFaixaInicial();
                int fim = faixa.getFaixaFinal();

                int tamanhoFaixa = (fim - inicio);

                int m3Cobrados = Math.min(consumoRestante, tamanhoFaixa);

                BigDecimal subtotal = faixa.getFaixaValorUnitario()
                        .multiply(BigDecimal.valueOf(m3Cobrados));

                valorTotal = valorTotal.add(subtotal);

                detalhamento.add(
                        DetalhamentoFaixaResponse.builder()
                                .faixa(
                                        FaixaResponse.builder()
                                                .inicio(inicio)
                                                .fim(fim)
                                                .build()
                                )
                                .m3Cobrados(m3Cobrados)
                                .valorUnitario(faixa.getFaixaValorUnitario())
                                .subtotal(subtotal)
                                .build()
                );

                consumoRestante -= m3Cobrados;
            }

            return CalculoTarifaResponse.builder()
                    .categoria(categoriaConsumidor.name())
                    .consumoTotal(consumoTotal)
                    .valorTotal(valorTotal)
                    .detalhamento(detalhamento)
                    .build();
        }catch (Exception exception){
            log.error("Ocorreu um erro no detalhamento do cálculo");
            throw new FalhaDetalhamentoException(exception.getMessage());
        }
    }


}
