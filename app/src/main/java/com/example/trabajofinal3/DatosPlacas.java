package com.example.trabajofinal3;

import java.util.ArrayList;
import java.util.List;

public class DatosPlacas {

    // este metodo devuelve una lista con los nombres de las placas
    public static List<String> obtenerPlacas() {
        List<String> placas = new ArrayList<>(); // lista para almacenar las placas

        placas.add("1. Placa B350");
        placas.add("2. Placa B450");
        placas.add("3. Placa B550");
        placas.add("4. Placa B650");
        placas.add("5. Placa B650E");
        placas.add("6. Placa B360");
        placas.add("7. Placa B365");
        placas.add("8. Placa B460");
        placas.add("9. Placa B560");
        placas.add("10. Placa B660");
        placas.add("11. Placa B760");
        return placas;
    }

    // este metodo devuelve un array de enteros con los identificadores de las imagenes asociadas a las placas.
    public static int[] obtenerImagenes() {
        try {
            // retornando las imagenes correspondientes a las placas
            return new int[]{
                    R.drawable.placab350,
                    R.drawable.placab450,
                    R.drawable.placab550,
                    R.drawable.placab650,
                    R.drawable.placab650e,
                    R.drawable.placab360,
                    R.drawable.placab365,
                    R.drawable.placab460,
                    R.drawable.placab560,
                    R.drawable.placab660,
                    R.drawable.placab760
            };
        } catch (Exception e) {
            System.err.println("DatosPlacas: Error al obtener las imagenes de las placas: " + e.getMessage());
            return new int[0];
        }
    }

    // este metodo devuelve una lista de consejos para cada placa base, incluyendo detalles sobre el socket y la memoria ram.
    public static List<String> obtenerConsejos() {
        List<String> consejos = new ArrayList<>();

        try {
            consejos.add("Placa B350:\n\n" +
                    "Socket: AM4 (Lanzado en 2017, el AM4 ha sido compatible con muchos modelos de procesadores Ryzen hasta la serie 5000, pero su ciclo de vida está llegando a su fin).\n\n" +
                    "Memoria RAM: Compatible solo con DDR4.\n\n" +
                    "Consejo: Si bien es una opción económica, ya está desactualizada para tareas de alta demanda. Si deseas una plataforma más futura, considera actualizar a una placa más nueva, como B550 o B650.");

            consejos.add("Placa B450:\n\n" +
                    "Socket: AM4 (Compatible con procesadores Ryzen hasta la serie 5000, pero este socket está llegando a su fin de vida útil).\n\n" +
                    "Memoria RAM: Compatible con DDR4 y algunas configuraciones DDR5.\n\n" +
                    "Consejo: Ideal si buscas un balance entre precio y rendimiento, pero no es la mejor opción a largo plazo para nuevas construcciones.");

            consejos.add("Placa B550:\n\n" +
                    "Socket: AM4 (A pesar de ser una opción más reciente, el AM4 sigue ofreciendo soporte hasta 2027, pero el futuro de este socket está limitado).\n\n" +
                    "Memoria RAM: Soporta DDR4 y algunas configuraciones DDR5.\n\n" +
                    "Consejo: Si eres un gamer o profesional que necesita un buen rendimiento en PCIe 4.0, la B550 sigue siendo una excelente elección, pero considera AM5 para una plataforma más futura.");

            consejos.add("Placa B650:\n\n" +
                    "Socket: AM5 (Introducido en 2022, este socket está asegurado hasta 2027 con soporte para procesadores Ryzen 7000 y posteriores).\n\n" +
                    "Memoria RAM: Exclusivamente compatible con DDR5, ofreciendo mayor rendimiento y más ancho de banda para tareas exigentes.\n\n" +
                    "Consejo: Si buscas una opción a largo plazo con soporte para PCIe 5.0 y DDR5, la B650 es una excelente opción para una construcción moderna.");

            consejos.add("Placa B650E:\n\n" +
                    "Socket: AM5 (Igual que la B650, el socket AM5 está asegurado hasta 2027, garantizando compatibilidad con futuros procesadores).\n\n" +
                    "Memoria RAM: Solo compatible con DDR5, ideal para entusiastas del rendimiento extremo.\n\n" +
                    "Consejo: Perfecta para usuarios que buscan un alto rendimiento en juegos y tareas profesionales de alto nivel.");

            consejos.add("Placa B360:\n\n" +
                    "Socket: LGA1151 (Este socket ha llegado al final de su vida útil y no es recomendable para nuevas construcciones).\n\n" +
                    "Memoria RAM: Compatible solo con DDR4.\n\n" +
                    "Consejo: Si ya tienes un sistema con este socket, puede ser útil para actualizaciones menores, pero no es adecuado para una nueva construcción en 2024.");

            consejos.add("Placa B365:\n\n" +
                    "Socket: LGA1151 (Similar al B360, este socket también está descontinuado, pero sigue siendo útil para procesadores Intel de 8ª y 9ª generación).\n\n" +
                    "Memoria RAM: Compatible con DDR4.\n\n" +
                    "Consejo: Si ya tienes este socket, es una opción económica para mejorar tu sistema, pero si estás construyendo desde cero, busca opciones más modernas.");

            consejos.add("Placa B460:\n\n" +
                    "Socket: LGA1200 (Compatible con procesadores Intel de 10ª generación, pero ha llegado al final de su ciclo de vida).\n\n" +
                    "Memoria RAM: Compatible con DDR4.\n\n" +
                    "Consejo: Si deseas actualizar un sistema sin gastar mucho, es una opción razonable, pero no es recomendable para nuevas construcciones.");

            consejos.add("Placa B560:\n\n" +
                    "Socket: LGA1200 (Compatible con CPUs Intel de 10ª y 11ª generación, pero está desactualizada en comparación con opciones más modernas).\n\n" +
                    "Memoria RAM: Compatible con DDR4 y soporta velocidades de RAM más altas.\n\n" +
                    "Consejo: Si buscas una opción económica para actualizar un sistema Intel de 10ª o 11ª generación, esta placa puede ser adecuada, pero considera opciones con soporte para DDR5.");

            consejos.add("Placa B660:\n\n" +
                    "Socket: LGA1700 (Este socket es compatible con los últimos procesadores de Intel de 12ª y 13ª generación, lo que le da una vida útil prolongada).\n\n" +
                    "Memoria RAM: Compatible con DDR4 y DDR5, lo que ofrece flexibilidad para ajustarse a tu presupuesto.\n\n" +
                    "Consejo: Si buscas una placa con soporte para PCIe 4.0 y DDR5 a un buen precio, la B660 es una excelente opción.");

            consejos.add("Placa B760:\n\n" +
                    "Socket: LGA1700 (Este socket es uno de los más recientes y es ideal para procesadores Intel de 12ª y 13ª generación).\n\n" +
                    "Memoria RAM: Compatible con DDR4 y DDR5, lo que te da acceso a las últimas tecnologías.\n\n" +
                    "Consejo: Si buscas soporte para las tecnologías más recientes, como DDR5 y PCIe 5.0, la B760 es una gran opción.");

        } catch (Exception e) {
            // captura de errores
            System.err.println("DatosPlacas: Error al agregar los consejos: " + e.getMessage());
        }
        return consejos;
    }
}
