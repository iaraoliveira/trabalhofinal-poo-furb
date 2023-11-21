package model;

import java.time.LocalDate;
import java.util.ArrayList;
import model.enums.CategoriaDespesa;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author iara9
 */
public class Conta {
    private double saldo;

    //listas de despesas e receitas
    private ArrayList<Despesa> despesas;
    private ArrayList<Receita> receitas;
    
    
    public Conta() {
        despesas = new ArrayList<>();
        receitas = new ArrayList<>();
    }
    
    public ArrayList<Despesa> getDespesas(){
        return despesas;
    }
    
    public ArrayList<Receita> getReceitas(){
        return receitas;
    }
    
    public void getLancamentos(){
        
    }
    
    /**
     * Inclui nova despesa e faz decremento no valor do saldo caso a data da despesa já tenha passado
     * @param despesa 
     */
    public void incluirDespesa(Despesa d){
        despesas.add(d);
        
        //decrementa saldo da conta
        LocalDate today = LocalDate.now();
        if (d.getData().isBefore(today) || d.getData().isEqual(today)){
            double s = saldo - d.getValor();
            setSaldo(s);
        }
    }
    
    /**
     * Inclui nova receita e faz incremento no valor do saldo caso a data da receita já tenha passado
     * @param receita
     */
    public void incluirReceita(Receita r){
        receitas.add(r);
        
        // incrementa saldo da conta
        LocalDate today = LocalDate.now();
        if (r.getData().isBefore(today) || r.getData().isEqual(today)){
            double s = saldo + r.getValor();
            setSaldo(s);
        }
    }

    /**
     * Este método é utilizado para consultar o saldo disponível em conta 
     * considerando os lancamentos com data igual ou menor que o dia informado pro parâmetro
     * @param data - data para filtragem dos lancamentos
     * @return saldo - valor com duas casa decimais após a vírgula referente ao saldo da conta até o dia especificado
     */
    public double getSaldo(LocalDate dt){
        
        double saldoPrevisto = 0;
        
        for (Receita r : receitas) {
            if (r.getData().isBefore(dt) || r.getData().isEqual(dt))
            saldoPrevisto = saldoPrevisto + r.getValor();
        }
        
        for (Despesa d : despesas) {
            if (d.getData().isBefore(dt) || d.getData().isEqual(dt))
            saldoPrevisto = saldoPrevisto - d.getValor();
        }
        
        
        return saldoPrevisto;
    }
    
    /**
     * Este método é utilizado para consultar o saldo disponível em conta no dia atual (now)
     * @return saldo - valor com duas casa decimais após a vírgula referente ao saldo atual da conta
     */
    public double getSaldo() {
        return saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
      
    
}
