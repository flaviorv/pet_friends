package com.agendamento.dominio;

public class Data {
    private String dia;
    private String hora;

    public Data(String dia, String hora) throws Exception {
        if (validarFormatoDia(dia) && validarFormatoHora(hora)) {
            this.dia = dia;
            this.hora = hora;
            System.out.println(this);
        } else {
            throw new Exception("Data não aceita.");
        }

    }

    @Override
    public String toString() {
        return dia + " " + hora;
    }

    public Boolean validarFormatoDia(String data) {
        String msgErro = ("Formato de data iválido. Exemplo correto: '26/07/25'.");

        String[] diaMesAno = data.split("/");

        try {
            Integer.parseInt(diaMesAno[0]);
            Integer.parseInt(diaMesAno[1]);
            Integer.parseInt(diaMesAno[2]);

        } catch (Exception e) {
            System.out.println(msgErro);
            System.out.println("Motivo: não aceita parse para inteiro.");
            return false;
        }

        if (diaMesAno.length != 3) {
            System.out.println(msgErro);
            System.out.println("Motivo: tamanho do array igual a: " + diaMesAno.length);
            return false;
        }


        for (int i = 0; i < diaMesAno.length; i++) {
            if (diaMesAno[i].length() != 2) {
                System.out.println(msgErro);
                if(i == 0) {
                    System.out.println("Motivo: dia " + diaMesAno[i]);
                }else if(i == 1) {
                    System.out.println("Motivo: mês " + diaMesAno[i]);
                }else if(i == 2) {
                    System.out.println("Motivo: ano " + diaMesAno[i]);
                }

                return false;
            }
        }

        return true;
    }

    public Boolean validarFormatoHora(String hora) {
        String msgErro = ("Formato de hora iválido. Exemplo correto: '16:24'.");

        String[] horaMinuto = hora.split(":");

        try {
            Integer.parseInt(horaMinuto[0]);
            Integer.parseInt(horaMinuto[1]);
        } catch (Exception e) {
            System.out.println(msgErro);
            System.out.println("Motivo: Não aceita parse para inteiro");
            return false;
        }

        if (horaMinuto.length != 2) {
            System.out.println(msgErro);
            System.out.println("Motivo: tamanho da array igual a: " + horaMinuto.length);
            return false;
        }

        for (int i = 0; i < horaMinuto.length; i++) {
            if (horaMinuto[i].length() != 2) {
                System.out.println(msgErro);
                if(i == 0) {
                    System.out.println("Motivo: horas igual a " + horaMinuto[i]);
                } else if (i == 1) {
                    System.out.println("Motivo: minutos igual a: " + horaMinuto[i]);
                }

                return false;
            }
        }

        return true;
    }

}
