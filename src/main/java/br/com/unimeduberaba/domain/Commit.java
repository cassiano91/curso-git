package br.com.unimeduberaba.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.unimeduberaba.BO.CommitBO;
import br.com.unimeduberaba.dicionario.TipoMudanca;

@Entity
public class Commit {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CommitId")
	@SequenceGenerator(name = "CommitId", sequenceName = "CommitId", allocationSize = 1)
	private Integer id;

	private LocalDateTime data;

	private String ambiente;

	private String versao;

	private String branch;

	private Integer v1;

	private Integer v2;

	private Integer v3;

	private String codigocommitgit;

	private String username;

	private String useremail;

	private String textoCommit;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public String getAmbiente() {
		return ambiente;
	}

	public void setAmbiente(String ambiente) {
		this.ambiente = ambiente;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Integer getV1() {
		return v1;
	}

	public void setV1(Integer v1) {
		this.v1 = v1;
	}

	public Integer getV2() {
		return v2;
	}

	public void setV2(Integer v2) {
		this.v2 = v2;
	}

	public Integer getV3() {
		return v3;
	}

	public void setV3(Integer v3) {
		this.v3 = v3;
	}

	public String getCodigocommitgit() {
		return codigocommitgit;
	}

	public void setCodigocommitgit(String codigocommitgit) {
		this.codigocommitgit = codigocommitgit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUseremail() {
		return useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getTextoCommit() {
		return textoCommit;
	}

	public void setTextoCommit(String textoCommit) {
		this.textoCommit = textoCommit;
	}

	public void montarSessao(TipoMudanca tipoMudanca) throws Exception {
		if (this.getAmbiente() == null || this.getAmbiente().trim().equals("")) {
			throw new Exception("Falta ambiente para montar a versão");
		}

		Commit ultimo = CommitBO.buscarUltimoPorAmbiente(this.getAmbiente());

		if (ultimo != null) {
			switch (tipoMudanca) {
			case GRANDE:
				this.setV1(ultimo.getV1() + 1);
				this.setV2(0);
				this.setV3(0);

				break;

			case MEDIA:
				this.setV1(ultimo.getV1());
				this.setV2(ultimo.getV2() + 1);
				this.setV3(0);

				break;

			default:
				this.setV1(ultimo.getV1());
				this.setV2(ultimo.getV2());
				this.setV3(ultimo.getV3() + 1);
				break;
			}

		} else {
			this.setV1(0);
			this.setV2(0);
			this.setV3(0);
		}

		this.setVersao(this.getV1() + "." + this.getV2() + "." + this.getV3());
	}
}