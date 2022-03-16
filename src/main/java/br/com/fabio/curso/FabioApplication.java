package br.com.fabio.curso;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.fabio.curso.domain.Categoria;
import br.com.fabio.curso.domain.Cidade;
import br.com.fabio.curso.domain.Cliente;
import br.com.fabio.curso.domain.Endereco;
import br.com.fabio.curso.domain.Estado;
import br.com.fabio.curso.domain.Pagamento;
import br.com.fabio.curso.domain.PagamentoComBoleto;
import br.com.fabio.curso.domain.PagamentoComCartao;
import br.com.fabio.curso.domain.Pedido;
import br.com.fabio.curso.domain.Produto;
import br.com.fabio.curso.domain.enums.EstadoPagamento;
import br.com.fabio.curso.domain.enums.TipoCliente;
import br.com.fabio.curso.repositories.CategoriaRepository;
import br.com.fabio.curso.repositories.CidadeRepository;
import br.com.fabio.curso.repositories.ClienteRepository;
import br.com.fabio.curso.repositories.EnderecoRepository;
import br.com.fabio.curso.repositories.EstadoRepository;
import br.com.fabio.curso.repositories.PagamentoRepository;
import br.com.fabio.curso.repositories.PedidoRepository;
import br.com.fabio.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class FabioApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FabioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade2));
		estado2.getCidades().addAll(Arrays.asList(cidade1, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail",
				"333.452.852-78", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("3351-2028","99952-4171"));
		
		Endereco e1 = new Endereco(null,"Rua Floral","300","Ap 402","Jardim","57014-522", cli1, cidade1);
		Endereco e2 = new Endereco(null,"Avenida muto","105","Sala 800","Centro","57085-000", cli1, cidade2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1); 
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
	}

}
