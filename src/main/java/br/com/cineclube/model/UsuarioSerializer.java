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

public class UsuarioSerializer extends StdSerializer<Set<Usuario>> {
	private static final long serialVersionUID = 1L;
	public UsuarioSerializer() {
		this(null);
	}
	public UsuarioSerializer(Class<Set<Usuario>> t) {
		super(t);
	}
	@Override
	public void serialize(Set<Usuario> tipo, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		Map<String, Object> usuarioMap = new HashMap<>();
		List<Map<String, Object>> tipoList = new ArrayList<>();
		for (Usuario u : tipo) {
			usuarioMap = new HashMap<>();
			usuarioMap.put("id", u.getId());
			usuarioMap.put("nome", u.getNome());
			tipoList.add(usuarioMap);
		}
		generator.writeObject(tipoList);
	}
}
   