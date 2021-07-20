package br.com.cineclube.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PessoaListSerializer extends StdSerializer<Set<Pessoa>> {
	private static final long serialVersionUID = 1L;
	public PessoaListSerializer() {
		this(null);
	}
	public PessoaListSerializer(Class<Set<Pessoa>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Pessoa> elenco, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> pessoaMap = new HashMap<>();
		List<Map<String, Object>> elencoList = new ArrayList<>();
		for (Pessoa p : elenco) {
			pessoaMap = new HashMap<>();
			pessoaMap.put("id", p.getId());
			pessoaMap.put("nome", p.getNome());
			elencoList.add(pessoaMap);
		}
		generator.writeObject(elencoList);
	}
	public void serialize_simples_exemplo(Set<Pessoa> elenco, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		List<String> listaSerializada = new ArrayList<>();
		for (Pessoa p : elenco)
			listaSerializada.add(p.getNome());
		generator.writeObject(listaSerializada);
	}
}
