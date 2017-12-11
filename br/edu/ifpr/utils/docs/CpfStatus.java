package br.edu.ifpr.utils.docs;

public enum CpfStatus {
	DOC_ERROR(-4), DOC_SIZE(-3), DOC_DV2(-2), DOC_DV1(-1), DOC_OK(0);

	private final Integer value;

	CpfStatus(int value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
