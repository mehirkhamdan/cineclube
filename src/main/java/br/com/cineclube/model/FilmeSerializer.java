package br.com.cineclube.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FilmeSerializer extends StdSerializer<Set<Filme>>{
	
	private static final long serialVersionUID = 1L;

	public FilmeSerializer() {
        this(null);
    }
    public FilmeSerializer(Class<Set<Filme>> t) {
        super(t);
    }    
    @Override
    public void serialize(Set<Filme> listaFilmes, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
    	Map<Long, String> filmeMap = new HashMap<>();
        for (Filme filme : listaFilmes)
        	filmeMap.put(filme.getId(), filme.getNome());
        generator.writeObject(filmeMap);
    }
}
