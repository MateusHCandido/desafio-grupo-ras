package com.grupo.ras.tarifa.service;

import com.grupo.ras.tarifa.controller.dto.tabela.CategoriaRequest;
import com.grupo.ras.tarifa.controller.dto.tabela.FaixaRequest;
import com.grupo.ras.tarifa.controller.dto.tabela.TabelaTarifariaCreateRequest;
import com.grupo.ras.tarifa.entity.CategoriaTarifaria;
import com.grupo.ras.tarifa.entity.FaixaConsumo;
import com.grupo.ras.tarifa.entity.TabelaTarifaria;
import com.grupo.ras.tarifa.enums.CategoriaConsumidor;
import com.grupo.ras.tarifa.enums.StatusTabelaTarifaria;
import com.grupo.ras.tarifa.repository.provider.TabelaTarifariaRepositoryProvider;
import com.grupo.ras.tarifa.service.exceptions.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TabelaTarifariaService {


    private final TabelaTarifariaRepositoryProvider tabelaTarifariaRepository;


    public void criarTabelaTarifaria(TabelaTarifariaCreateRequest request){
       log.info("Criando tabela tarifaria");
       TabelaTarifaria tabelaTarifaria = new TabelaTarifaria();

       tabelaTarifaria.setNome(request.nome());
       tabelaTarifaria.setDataVigencia(request.dataVigencia());
       tabelaTarifaria.setStatus(StatusTabelaTarifaria.ATIVA);

       List<CategoriaTarifaria> categorias =
               request
                       .categorias()
                       .stream()
                       .map(categoriaRequest -> criarCategoria(categoriaRequest, tabelaTarifaria))
                       .toList();

       tabelaTarifaria.setCategorias(categorias);

       tabelaTarifariaRepository.inativarTabelasAtivas();
       tabelaTarifariaRepository.save(tabelaTarifaria);
   }

    private CategoriaTarifaria criarCategoria(CategoriaRequest request, TabelaTarifaria tabelaTarifaria){
       log.info("Criando categoria tarifária");
       validarFaixas(request.faixas());

       CategoriaTarifaria categoria = new CategoriaTarifaria();

       categoria.setCategoriaConsumidor(CategoriaConsumidor.valueOf(request.categoria()));
       categoria.setTabelaTarifaria(tabelaTarifaria);

       List<FaixaConsumo> faixasConsumo = request
               .faixas()
               .stream()
               .map(faixa -> criarFaixa(faixa, categoria))
               .toList();

       categoria.setFaixas(faixasConsumo);

       return categoria;
   }

    private void validarFaixas(List<FaixaRequest> faixas){
       faixas.sort(Comparator.comparing(FaixaRequest::faixaInicio));

       for (int i = 0; i < faixas.size(); i++){
           FaixaRequest atual = faixas.get(i);

           if (atual.faixaInicio() >= atual.faixaFim()) throw new OrdemFaixasInvalidaException();
           if (i == 0 && atual.faixaInicio() != 0) throw new CoberturaNaoCompletaException();
           if (i > 0 ) {
               FaixaRequest anterior = faixas.get(i-1);
               if (atual.faixaInicio() <= anterior.faixaFim()) throw new SobreposicaoException();
           }
       }
   }

    private FaixaConsumo criarFaixa(FaixaRequest request, CategoriaTarifaria categoriaTarifaria){
       log.info("Criano faixa de consumo");

       FaixaConsumo faixaConsumo = new FaixaConsumo();

       faixaConsumo.setFaixaInicial(request.faixaInicio());
       faixaConsumo.setFaixaFinal(request.faixaFim());
       faixaConsumo.setFaixaValorUnitario(request.valor());

       faixaConsumo.setCategoriaTarifaria(categoriaTarifaria);
       faixaConsumo.setFaixaDescricao(categoriaTarifaria.getCategoriaConsumidor().name());

       return faixaConsumo;
   }

    public List<TabelaTarifaria> listarTabelasTarifaria(){
        log.info("Efetuando consulta de listagem de todas as tabelas tarifarias");
        List<TabelaTarifaria> tabelas = tabelaTarifariaRepository.findAll();

        if (tabelas.isEmpty()) throw new SemRegistroTabelaException();
        return tabelas;
    }

    public void excluirTabelaTarifaria(Long tabelaTarifariaId){
        try{
            log.info("Efetuando exclusão da tabela tarifária de id {}", tabelaTarifariaId);
            tabelaTarifariaRepository.deleteBy(tabelaTarifariaId);
        }catch (Exception exception){
            log.error("Ocorreu um erro na exclusão da tabela tarifária de id {}", tabelaTarifariaId);
            throw new FalhaExclusaoTabelaTarifariaException(exception.getMessage());
        }
    }

}
