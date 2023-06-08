package br.com.controlegastos.persistencia.api.utils.tools;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapList {

    public MapList() {
    }

    public static void printarChaveValores(Class classeEntidade) throws Exception {
        Map<String,String> mapa = obterCampos(classeEntidade);
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public static Map<String, String> obterCampos(Class classeEntidade)throws Exception {
        try{
            Field[] campos = classeEntidade.getDeclaredFields();
            Map<String, String> tiposNomes = new HashMap<>();

            for (Field cp : campos) {
                String nome = cp.getName();
                String tipoCampo = cp.getType().getSimpleName();
                tiposNomes.put(nome, tipoCampo);
            }

            if(tiposNomes.size() == 0 ){
                throw new Exception("Classe "+classeEntidade.getName()+" não contém campos.");
            }

            return tiposNomes;
        }catch (Exception ex){
            throw ex;
        }
    }

    public static Map<String, Object> toMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(obj);
            map.put(name, value);
        }
        return map;
    }
}
