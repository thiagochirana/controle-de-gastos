package br.com.controlegastos.util;

import br.com.controlegastos.entidades.Gastos;
import br.com.controlegastos.entidades.records.DadosRegistroDeGasto;
import br.com.controlegastos.entidades.records.DadosRelatorioGastosVeiculoUnico;
import br.com.controlegastos.entidades.records.DadosRelatoriosMensaisGastos;
import com.itextpdf.text.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class GeradorRelatorio {

    private static Logger LOG = LoggerFactory.getLogger(GeradorRelatorio.class);

    public GeradorRelatorio() {
    }

    public void gerarRelatorioGastosMensal(List<DadosRelatoriosMensaisGastos> lista, String caminho) {
        try{
            LOG.info("Recebi a solicitação para gerar relatorio de gastos mensais, vou preparar o relatório.");
            Document doc = new Document();
            caminho += "relatorio-de-gastos-mensais.pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(caminho));
            doc.open();

            Paragraph titulo = new Paragraph("Relatório de Gastos Mensais\n");
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            for (DadosRelatoriosMensaisGastos data : lista) {
                Paragraph subTitulo = new Paragraph("Período/Mês: "+data.mesAno(), FontFactory.getFont(FontFactory.COURIER_BOLD,14));
                doc.add(subTitulo);
                doc.add(new Paragraph("Descricão do Gasto: "+data.nomeGasto()));
                doc.add(new Paragraph("Valor total no mês: "+data.valor()));
                doc.add(new Paragraph("\n"));
            }

            doc.close();

            LOG.info("Relatório em PDF gerado com sucesso e salvo em  "+caminho);

        } catch (DocumentException de){
            LOG.info("Houve um erro ao gerar o relatório de gastos mensais.",de);
        } catch (IOException io){
            LOG.error("Houve um erro ao salvar arquivo no caminho "+caminho,io);
        }
    }

    public void gerarRealatorioGastoMensalParaUmVeiculo(DadosRelatorioGastosVeiculoUnico dados, String caminho){
        try{
            LOG.info("Gerarei relatorio para veiculo "+dados.veiculo()+" "+dados.modelo()+" "+dados.marca()+" no mês de "+dados.mesAno());

            Document doc = new Document();
            caminho += "relatorio-gastos-mensais-veiculo-"+dados.veiculo().trim()+".pdf";
            PdfWriter.getInstance(doc, new FileOutputStream(caminho));
            doc.open();

            Paragraph titulo = new Paragraph("Relatório de Gastos Mensais\n");
            titulo.setAlignment(Element.ALIGN_CENTER);
            doc.add(titulo);

            doc.add(new Paragraph("Veiculo: "+dados.veiculo()));
            doc.add(new Paragraph("Marca: "+dados.marca()));
            doc.add(new Paragraph("Modelo: "+dados.modelo()));
            doc.add(new Paragraph("Mes/Ano: "+dados.mesAno()));

            doc.add(new Paragraph("\n\n"));

            for (Gastos ga : dados.listaGastosMensal()){
                doc.add(new Paragraph("Categoria: "+ga.getDescricao()));
                doc.add(new Paragraph("Valor: "+ga.getValor()));
            }

            doc.add(new Paragraph("\n\n\nValor Total dos Gastos: "+dados.valorTotal()));

            doc.close();

            LOG.info("Relatório em PDF gerado com sucesso e salvo em  "+caminho);

        }catch (Exception e){
            LOG.info("Houve um erro ao tentar gerar o relatório mensal de gastos para veículo "+dados.veiculo()+" "+dados.mesAno(),e);
        }
    }
}
