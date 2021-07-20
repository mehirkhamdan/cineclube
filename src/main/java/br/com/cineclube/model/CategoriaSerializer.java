package br.com.cineclube.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CategoriaSerializer extends StdSerializer<Set<Categoria>>{
	
	private static final long serialVersionUID = 1L;

	public CategoriaSerializer() {
        this(null);
    }
    public CategoriaSerializer(Class<Set<Categoria>> t) {
        super(t);
    }    
    @Override
    public void serialize(Set<Categoria> listaCategorias, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
    	Map<Long, String> catMap = new HashMap<>();
        for (Categoria cat: listaCategorias)
        	catMap.put(cat.getId(), cat.getNome());
        generator.writeObject(catMap);
    }
}