package br.com.unimeduberaba;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.unimeduberaba.BO.CommitBO;
import br.com.unimeduberaba.dicionario.TipoMudanca;
import br.com.unimeduberaba.domain.Commit;
import br.com.unimeduberaba.util.Arquivo;
import br.com.unimeduberaba.util.Json;
import br.com.unimeduberaba.util.slack.Slack;
import br.com.unimeduberaba.util.slack.domain.Anexo;
import br.com.unimeduberaba.util.slack.domain.Envio;

public class App {
	private static Scanner sc1 = new Scanner(System.in);

	private static Boolean validaCaminho(String caminho) {
		File folder = new File(caminho + "\\.git");

		String patternString = "([a-zA-Z]:)?(\\\\[a-zA-Z0-9_.-]+)+\\\\?";

		Pattern pattern = Pattern.compile(patternString);

		Matcher matcher = pattern.matcher(caminho);

		return matcher.matches() && folder.exists();
	}

	public static String getTextoTerminal(String pergunta) {
		System.out.print(pergunta);

		String resposta = sc1.nextLine();
		System.out.println(resposta);
//		sc1.close();

		return resposta;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(" LINHA A SER EDITADA ");

		String caminho = "";
//		System.out.println(Json.toJson(args));

		if (args == null || args.length == 0 || args[0] == null || args[0].trim().equals("")) {
			caminho = getTextoTerminal("Digite a pasta do projeto a ser cometido:");
		} else {
			caminho = args[0];
		}

		if (!validaCaminho(caminho)) {
			throw new Exception("Pasta inválida");
		}

		TipoMudanca tipoMudanca = null;
		String resp = getTextoTerminal("Essa mudança foi de Fluig ou Trello? F/T: ").trim().toUpperCase();

		if (resp != null) {
			System.err.println(resp);

			switch (resp.trim().toUpperCase()) {
			case "F":
			case "FLUIG":
				System.err.println(1);
				tipoMudanca = TipoMudanca.PEQUENA;
				break;

			case "T":
			case "TRELO":
			case "TRELLO":
				System.err.println(2);

				resp = getTextoTerminal("Vai haver mudança de versão? S/N: ");
				if (resp.trim().toUpperCase().equals("S")) {
					tipoMudanca = TipoMudanca.GRANDE;
				} else if (resp.trim().toUpperCase().equals("N")) {
					tipoMudanca = TipoMudanca.MEDIA;
				} else {
					throw new Exception("Resposta inválida");
				}

				break;
			default:
				System.err.println(3);
				throw new Exception("Resposta inválida");
			}
		}
		System.out.println("--- " + tipoMudanca);

		if (tipoMudanca == null) {
			throw new Exception("Resposta inválida");
		}

		String[] ambienteArray = caminho.split("\\\\");
		String branch = Arquivo.executarComando("git --git-dir=" + caminho + "\\.git rev-parse --abbrev-ref HEAD");

		Arquivo.executarComando("git --git-dir=" + caminho + "\\.git add .");

		String textoCommit = getTextoTerminal("Texto do commit: ");
		if (textoCommit == null || textoCommit.trim().equals("")) {
			throw new Exception("Texto do commit inválido");
		}

		System.out.println(
				Arquivo.executarComando("git --git-dir=" + caminho + "\\.git commit -m \"" + textoCommit + "\""));

		String pull = Arquivo.executarComando("git --git-dir=" + caminho + "\\.git pull");

		System.out.println(pull);
		if (pull.contains("CONFLICT")) {
			throw new Exception("Commit com conflito: "
					+ Arquivo.executarComando("git --git-dir=" + caminho + "\\.git diff --name-only --diff-filter=U"));
		}

		System.out.println(Arquivo.executarComando("git --git-dir=" + caminho + "\\.git push origin " + branch));

		Commit obj = new Commit();
		obj.setData(LocalDateTime.now());
		obj.setAmbiente(ambienteArray[ambienteArray.length - 1]);
		obj.setBranch(branch);
		obj.montarSessao(tipoMudanca);
		obj.setUsername(Arquivo.executarComando("git --git-dir=" + caminho + "\\.git config user.name"));
		obj.setUseremail(Arquivo.executarComando("git --git-dir=" + caminho + "\\.git config user.email"));
		obj.setTextoCommit(textoCommit);

		// Pegando o código do commit
		obj.setCodigocommitgit(Arquivo.executarComando("git --git-dir=" + caminho + "\\.git log --pretty=%H -1"));

		System.out.println(Json.toJson(obj));
		CommitBO bo = new CommitBO();
		bo.salvar(obj);
		System.out.println(Json.toJson(obj));

		Envio envio = new Envio(obj.getUsername() + " fez um commit");

//		String link = 
//				"https://gitlab.com/unimeduberaba/plsweb/" + obj.getAmbiente() + "/commit/" + obj.getCodigocommitgit();

		String link = Arquivo.executarComando("git --git-dir=" + caminho + "\\.git remote get-url origin");
		System.out.println(link);
		link = link.replaceAll("git@", "https://");
		System.out.println(link);
		link = link.replaceAll("\\.git", "/commit/" + obj.getCodigocommitgit());
		System.out.println(link);

		Anexo anexo = new Anexo();
		anexo.setAuthorName(obj.getUsername());
		anexo.setTitle("Commit");
		anexo.setTitleLink(link);
		anexo.setText(textoCommit);

		envio.addAttachments(anexo);

		Slack.enviar(envio);
		System.out.println(Json.toJson(envio));

//		List<Commit> lista = bo.buscarTodos();
//
//		System.out.println(lista.size());
//		for (Commit ls : lista) {
//			System.out.println(ls.getId() + " - " +  ls.getData());
//		}
	}
}
