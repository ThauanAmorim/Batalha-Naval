package Arqs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**Classe para atribuir persistência de dados ao salvar objetos no formato XML em uma pasta chamada Dados que será criada junto ao executável.
* @author Thauan Amorim
*/


public class DAO {
	private static XStream xstream = new XStream(new StaxDriver());
	private static PrintWriter gravador;
	private static FileReader leitor;
	
	/**Método para salvar uma lista de players.
	* @author Thauan Amorim
	* @param  player Jogador – jogador que será adicionado a lista recuperada e logo após salvo no computador.
	*/

	
	public static void SalvarListaDePlayers(Jogador player){
		File file = new File(Diretorios.ListaDePlayers());
		xstream.alias("Jogador", Jogador.class);
		
		DAO.VerificarExistencia(file.getPath());
		
		try {
			ListaDePlayers listaDePlayers = DAO.LerListaDePlayers();
			listaDePlayers.addPlayer(player.getUsuario());
			
			gravador = new PrintWriter(file);
			
			gravador.print(xstream.toXML(listaDePlayers));
			gravador.flush();
			gravador.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para salvar uma lista de players.
	* @author Thauan Amorim
	* @param  listaDePlayers ListaDePlayers – lista de jogadores que será salva no computador.
	*/
	
	public static void SalvarListaDePlayers(ListaDePlayers listaDePlayers){
		File file = new File(Diretorios.ListaDePlayers());
		xstream.alias("Jogador", Jogador.class);
		
		DAO.VerificarExistencia(file.getPath());
		
		try {
			gravador = new PrintWriter(file);
			
			gravador.print(xstream.toXML(listaDePlayers));
			gravador.flush();
			gravador.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para ler uma lista de players.
	* @author Thauan Amorim
	* @return ListaDePlayers – lista de jogadores
	*/
	
	public static ListaDePlayers LerListaDePlayers(){
		File file = new File(Diretorios.ListaDePlayers());
		xstream.alias("Jogador", Jogador.class);
		
		DAO.VerificarExistencia(file.getPath());
		
		try {
			leitor = new FileReader(file);
			ListaDePlayers listaDePlayers = (ListaDePlayers) xstream.fromXML(leitor);
			return listaDePlayers;
			
		}catch(Exception e) {
			return new ListaDePlayers();
		}
	}
	
	/**Método para deletar a conta de um jogador pelo userName.
	* @author Thauan Amorim
	* @param  userName String – nome de usuário do player que terá a conta deletada
	*/

	public static void DeletarConta(String userName) {
		File file = new File(Diretorios.DadosDoPlayer(userName));
		
		try {
			ListaDePlayers listaDePlayers = DAO.LerListaDePlayers();
			listaDePlayers.removerPlayer(userName);
			DAO.SalvarListaDePlayers(listaDePlayers);
			DAO.solicitarSalvamentoDeRelatorios(DAO.LerPlayer(userName));
			System.gc();
			file.delete(); //arquivo do user deletado
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para substituir o nome de um usuário por outro na lista com o nome de todos os users cadastrados no sistema.
	* @author Thauan Amorim
	* @param  userName String – nome de usuário do player que será substituído.
	* @param  novoUserName String – novo nome do user que será substituido
	*/

	public static void substituirPlayerNameNaListaDeTodosOsUsers(String userName, String novoUserName) {
		File file = new File(Diretorios.DadosDoPlayer(userName));
		
		try {
			Jogador player = DAO.LerPlayer(userName);
			player.setUsuario(novoUserName);
			DAO.SalvarPlayer(player);
			
			ListaDePlayers lista = DAO.LerListaDePlayers();
			lista.substituirNome(userName, novoUserName);
			DAO.SalvarListaDePlayers(lista);
			
			System.gc();
			file.delete(); //deleta o arquivo com antigo nome
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para salvar via persistência de dados um jogador.
	* @author Thauan Amorim
	* @param player Jogador – jogador que será salvo.
	*/

	public static void SalvarPlayer(Jogador player){
		String diretorio = Diretorios.DadosDoPlayer(player.getUsuario());
		VerificarExistencia(diretorio);
		
		xstream.alias("Jogador", Jogador.class);
		try {
			gravador = new PrintWriter(new File(diretorio));
			
			gravador.print(xstream.toXML(player));
			gravador.flush();
			gravador.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		DAO.SalvarListaDePlayers(player);
	}
	
	/**Método para ler os dados um jogador previamente salvo no sistema.
	* @author Thauan Amorim
	* @param userName String – nome do jogador que será lido.
	* @return Jogador – objeto Jogador com os dados.
	*/

	public static Jogador LerPlayer(String userName){
		File file = new File(Diretorios.DadosDoPlayer(userName));
		
		xstream.alias("Jogador", Jogador.class);
		try {
			leitor = new FileReader(file);
			Jogador player = (Jogador) xstream.fromXML(leitor);
			return player;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
        
        return null;
	}
	
	/**Método para salva o histórico da partida do player
	* @author Thauan Amorim
	* @param userName String – nome do jogador que terá o histórico salvo.
	* @param texto String – texto que representa o histórico da partida.
	*/

	public static void SalvarHistoricoDePartida(String userName, String texto){
		VerificarExistencia(Diretorios.diretorioHistoricoDePartida(userName));
		
		File file = new File(Diretorios.diretorioHistoricoDePartida(userName));
		try {
			HistoricoDePartida historicoDePartida = DAO.LerHistoricoDePartidas(userName);
			historicoDePartida.addHistorico(texto);
			gravador = new PrintWriter(file);
			gravador.print(xstream.toXML(historicoDePartida));
			gravador.flush();
			gravador.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**Método para ler o histórico de partida previamente salvo no sistema.
	* @author Thauan Amorim
	* @param userName String – nome do jogador que tem os dados da partida.
	* @return HistoricoDePartida – objeto HistoricoDePartida com os dados.
	*/

	public static HistoricoDePartida LerHistoricoDePartidas(String userName){
		VerificarExistencia(Diretorios.diretorioHistoricoDePartida(userName));
		
		File file = new File(Diretorios.diretorioHistoricoDePartida(userName));
		try {
			leitor = new FileReader(file);
			HistoricoDePartida historicoDePartida = (HistoricoDePartida) xstream.fromXML(leitor);
			leitor.close();
			return historicoDePartida;
			
		}catch (Exception e) {
			return new HistoricoDePartida();
		}
	}

	/**Método para ler o histórico de partida previamente salvo no sistema.
	* @author Thauan Amorim
	* @param userName String – nome do jogador que tem os dados da partida.
	* @return ArrayList<String> – arraylist com as Strings de logs da partida.
	*/

	public static ArrayList<String> LerHistoricoDePartidasArrayList(String userName) {
		ArrayList<String> informacoes = new ArrayList<String>();
		
		VerificarExistencia(Diretorios.DadosDoPlayer(userName));
		String aux = "";
		
		try {
			
			String logs = DAO.LerHistoricoDePartidas(userName).getHistorico();
			String[] logsSeparados = (!logs.equals(""))? logs.split("\n") : new String[0];
			for(String valores : logsSeparados) {
				if(!valores.equals("") && valores.charAt(0) == '-') aux += valores + "\n";
				else if(!valores.equals("") && valores.charAt(0) != '-'){
					if(!(aux.equals(""))) informacoes.add(aux);
					informacoes.add(valores);
					aux = "";
				}
			}
			if(!(aux.equals(""))) informacoes.add(aux);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return informacoes;
	}
	
	/**Método para ler o histórico de pontos do usuário previamente salvo no sistema.
	* @author Thauan Amorim
	* @param userName String – nome do jogador que tem os dados de pontos.
	* @return HistoricoDePontos – objeto HistoricoDePontos com todo o histórico salvo nele.
	*/

	public static HistoricoDePontos LerHistoricoDePontos(String userName){
		VerificarExistencia(Diretorios.diretorioHistoricoDePontos(userName));
		File file = new File(Diretorios.diretorioHistoricoDePontos(userName));
		
		try {
			leitor = new FileReader(file);
			HistoricoDePontos historicoDePontos = (HistoricoDePontos) xstream.fromXML(leitor);
			leitor.close();
			return historicoDePontos;
			
		}catch (Exception e) {
			return new HistoricoDePontos();
		}
	}
	
	/**Método para salvar o histórico de pontos de um jogador
	* @author Thauan Amorim
	* @param player Jogador – objeto jogador com os dados já inseridos dentro dele.
	*/

	public static void salvarHistoricoDePontos(Jogador player) {
		VerificarExistencia(Diretorios.diretorioHistoricoDePontos(player.getUsuario()));
		File file = new File(Diretorios.diretorioHistoricoDePontos(player.getUsuario()));
		
		try {
			HistoricoDePontos historicoDePontos = DAO.LerHistoricoDePontos(player.getUsuario());
			
			ArrayList<Integer> valores = new ArrayList<>();
			valores.add(player.getPartidas()); valores.add(player.getPontuacao());
			historicoDePontos.addDados(valores);
			
			gravador = new PrintWriter(file);
			gravador.print(xstream.toXML(historicoDePontos));
			gravador.flush();
			gravador.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para solicitar o salvamento dos relatórios de vitorias e derrotas do player.
	* @author Thauan Amorim
	* @param player Jogador – objeto jogador.
	*/

	public static void solicitarSalvamentoDeRelatorios(Jogador player) {
		DAO.salvarRelatorio(player, DAO.separarVitoriasDeDerrotas(player).get(0), true);
		DAO.salvarRelatorio(player, DAO.separarVitoriasDeDerrotas(player).get(1), false);
	}
	
	/**Método para salvar um relatório do player.
	* @author Thauan Amorim
	* @param player Jogador – objeto jogador.
	* @param texto String – texto que será salvo dentro do relatório.
	* @param vitoria boolean – determina se o valor foi de vitória ou derrota.
	*/

	private static ArrayList<String> separarVitoriasDeDerrotas(Jogador player) {
		 ArrayList<String> listaHistoricoDePartidas = DAO.LerHistoricoDePartidasArrayList(player.getUsuario());
		 
		 ArrayList<String> listaDeVitoriasDerrotas = new ArrayList<>();
		
		 String vitoriaString = "";
		 String derrotaString = "";
		 try {
			 for(int i = 0; i < listaHistoricoDePartidas.size(); i += 2) {
				 String auxiliar = listaHistoricoDePartidas.get(i)+"\n";
				 
				 if(listaHistoricoDePartidas.get(i+1).contains("-" + player.getUsuario() + " Ganhou")) {
					 vitoriaString += auxiliar + listaHistoricoDePartidas.get(i+1) + "\n\n";
				 }else {
					 derrotaString += auxiliar + listaHistoricoDePartidas.get(i+1) + "\n\n";
				 }
			 }
			 
			 listaDeVitoriasDerrotas.add(vitoriaString);
			 listaDeVitoriasDerrotas.add(derrotaString);
			 return listaDeVitoriasDerrotas;
			 
		 }catch(Exception e) {
			 return null;
		 }
	}
	
	/**Método para salvar um relatório do player.
	* @author Thauan Amorim
	* @param player Jogador – objeto jogador.
	* @param texto String – texto que será salvo dentro do relatório.
	* @param vitoria boolean – determina se o valor foi de vitória ou derrota.
	*/

	private static void salvarRelatorio(Jogador player, String texto, boolean vitoria) {
		VerificarExistencia(Diretorios.diretorioRelatoriosVitoriasPlayer(player.getUsuario()));
		// criação do documento
        Document document = new Document();
        try {
        	if(vitoria) {
        		PdfWriter.getInstance(document, new FileOutputStream(Diretorios.diretorioRelatoriosVitoriasPlayer(player.getUsuario())));
        		
        	}else {
        		PdfWriter.getInstance(document, new FileOutputStream(Diretorios.diretorioRelatoriosDerrotasPlayer(player.getUsuario())));
        		
        	}
        	
            document.open();

            // adicionando um parágrafo no documento
            document.add(new Paragraph(texto));
            document.close();
        }	
        catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	/**Método para verificar se um caminho já existe no PC, caso não exista ele garante a criação do mesmo.
	* @author Thauan Amorim
	* @param path String – caminho do diretório para verificação.
	*/
	
	@SuppressWarnings("static-access")
	public static void VerificarExistencia(String path){
		String[] pathDividido = path.split("");
		File file = new File(path);
		
		//remove o nome do arquivo do path e tranforma em um array
		for(int i = pathDividido.length -1; i > 0; i--) {
			if(pathDividido[i].equals(file.separator)) break;
			
			pathDividido[i] = "";
			
		}
		
		//tranforma de array para um string sem o nome do arquivo no final.
		String dir = "";
		for(String caracter : pathDividido) {
			dir += caracter;
		}
		
		File diretorio = new File(dir);
		
		try {
			
			if(!diretorio.exists()) diretorio.mkdirs();
			if(!file.exists()) file.createNewFile();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**Método para se um jogador já está cadastrado no sistema do game.
	* @author Thauan Amorim
	* @param userName String – nome do usuário que será verificado.
	* @param email String – email do usuário que será verificado.
	* @return boolean – retorno se já está cadastrado ou não.
	*/

	public static boolean VerificarUserJaCadastrado(String userName, String email){
		File file = new File(Diretorios.DadosDoPlayer(userName));
		if(file.exists()) return true;
		
		for(String userNames : DAO.LerListaDePlayers().getListaDePlayers()) {
			Jogador player = DAO.LerPlayer(userNames);
			if(email != null && player.getEmail().equals(email)) return true;
		}
		
		return false;
	}
}