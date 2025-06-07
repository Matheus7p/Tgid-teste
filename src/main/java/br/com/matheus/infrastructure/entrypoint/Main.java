package br.com.matheus.infrastructure.entrypoint;


import br.com.matheus.infrastructure.configuration.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();

    }
}