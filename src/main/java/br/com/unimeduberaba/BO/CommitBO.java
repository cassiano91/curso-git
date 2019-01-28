package br.com.unimeduberaba.BO;

import java.util.List;

import br.com.unimeduberaba.DAO.CommitDAO;
import br.com.unimeduberaba.domain.Commit;

public class CommitBO {
	private CommitDAO dao;

	public CommitBO() {
		dao = new CommitDAO();
	}

	public List<Commit> buscarTodos() {
		return dao.buscarTodos();
	}

	public static Commit buscarUltimoPorAmbiente(String ambiente) {
		return new CommitBO().buscarUltimoPorAmbiente(ambiente, true);
	}

	private Commit buscarUltimoPorAmbiente(String ambiente, Boolean buscar) {
		if (buscar) {
			return dao.buscarUltimoPorAmbiente(ambiente);
		} else {
			return null;
		}
	}

	public Integer salvar(Commit commit) throws Exception {
		if (commit.getData() == null) {
			throw new Exception("Sem data");
		}

		if (commit.getAmbiente() == null || commit.getAmbiente().trim().equals("")) {
			throw new Exception("Sem Ambiente");
		}

		if (commit.getVersao() == null) {
			throw new Exception("Sem Versao");
		}

		if (commit.getV1() == null) {
			throw new Exception("Sem V1");
		}

		if (commit.getV2() == null) {
			throw new Exception("Sem V2");
		}

		if (commit.getV3() == null) {
			throw new Exception("Sem V3");
		}

		if (commit.getBranch() == null || commit.getBranch().trim().equals("")) {
			throw new Exception("Sem Branch");
		}

		return dao.salvar(commit);
	}
}