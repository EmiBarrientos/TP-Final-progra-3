package com.example.demo.mapper.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ReflectionMapper {



        public static void actualizarCamposNoNulos(Object origen, Object destino) {
            if (origen == null || destino == null) {
                throw new IllegalArgumentException("Los objetos no pueden ser null");
            }

            Class<?> claseOrigen = origen.getClass();
            Class<?> claseDestino = destino.getClass();

            // Obtener todos los getters del origen y setters del destino
            Map<String, Method> gettersOrigen = obtenerGetters(claseOrigen);
            Map<String, Method> settersDestino = obtenerSetters(claseDestino);

            for (Map.Entry<String, Method> entry : gettersOrigen.entrySet()) {
                String nombreCampo = entry.getKey();
                Method getter = entry.getValue();

                Method setter = settersDestino.get(nombreCampo);
                if (setter != null) {
                    try {
                        Object valor = getter.invoke(origen);
                        if (valor != null) {
                            setter.invoke(destino, valor);
                        }
                    } catch (Exception e) {
                        throw new RuntimeException("Error al copiar el campo '" + nombreCampo + "'", e);
                    }
                }
            }
        }

        private static Map<String, Method> obtenerGetters(Class<?> clazz) {
            Map<String, Method> getters = new HashMap<>();
            while (clazz != null) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (esGetter(method)) {
                        String nombreCampo = extraerNombreCampo(method.getName());
                        getters.put(nombreCampo, method);
                    }
                }
                clazz = clazz.getSuperclass();
            }
            return getters;
        }

        private static Map<String, Method> obtenerSetters(Class<?> clazz) {
            Map<String, Method> setters = new HashMap<>();
            while (clazz != null) {
                for (Method method : clazz.getDeclaredMethods()) {
                    if (esSetter(method)) {
                        String nombreCampo = extraerNombreCampo(method.getName());
                        setters.put(nombreCampo, method);
                    }
                }
                clazz = clazz.getSuperclass();
            }
            return setters;
        }

        private static boolean esGetter(Method method) {
            return method.getName().startsWith("get") &&
                    method.getParameterCount() == 0 &&
                    !void.class.equals(method.getReturnType());
        }

        private static boolean esSetter(Method method) {
            return method.getName().startsWith("set") &&
                    method.getParameterCount() == 1;
        }

        private static String extraerNombreCampo(String metodo) {
            // Convierte getNombreCampo → nombreCampo
            return Character.toLowerCase(metodo.charAt(3)) + metodo.substring(4);
        }


}
