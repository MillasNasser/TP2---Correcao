/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Default.Chave;
import Default.Comando;
import Default.Corredor;
import Default.Diamante;
import Default.Local;
import Default.Machado;
import Default.MachadoBronze;
import Default.MachadoFerro;
import Default.MachadoOuro;
import Default.Mapa;
import Default.Ouro;
import Default.Pegavel;
import Default.Pocao;
import Default.Porta;
import Default.Sala;
import Default.Troll;
import Default.TrollCaverna;
import Default.TrollGuerreiro;
import Exceptions.ItemException;
import Exceptions.PersonagemException;
import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Principal extends javax.swing.JPanel {	
	private Mapa mapa;
	private JFrame frame;
	
	public Principal() {
		initComponents();
	}
	
	public Principal(Mapa mapa, JFrame frame) {
		this.mapa = mapa;
		this.frame = frame;
		initComponents();
        
        btnAcaoSalaOuro.addActionListener(acaoBotaoMover("Ouro", quantidadeOuro, Ouro.class));
        btnAcaoSalaDiamante.addActionListener(acaoBotaoMover("Diamante", quantidadeDiamante, Diamante.class));
        btnAcaoSalaPocao.addActionListener(acaoBotaoMover("Pocao", quantidadePocao, Pocao.class));
        btnAcaoSalaChave.addActionListener(acaoBotaoMover("Chave", quantidadeChaves, Chave.class));
        btnAcaoMachadoOuro.addActionListener(acaoBotaoMover("Machado de Ouro", quantidadeMachadoOuro, MachadoOuro.class));
        btnAcaoMachadoBronze.addActionListener(acaoBotaoMover("Machado de Bronze", quantidadeMachadoBronze, MachadoBronze.class));
        btnAcaoMachadoFerro.addActionListener(acaoBotaoMover("Machado de Ferro", quantidadeMachadoFerro, MachadoFerro.class));
        
        btnLargarMachadoOuro.addActionListener(acaoBotaoLargar("Machado de Ouro", quantidadeMachadoOuro, MachadoOuro.class));
        btnLargarMachadoBronze.addActionListener(acaoBotaoLargar("Machado de Bronze", quantidadeMachadoBronze, MachadoBronze.class));
        btnLargarMachadoFerro.addActionListener(acaoBotaoLargar("Machado de Ferro", quantidadeMachadoFerro, MachadoFerro.class));
        btnLargarPocao.addActionListener(acaoBotaoLargar("Pocao", quantidadePocao, Pocao.class));
		atualizaGUI();
	}
	
	private void atualizaGUI(){
		setAllLabelsToDefault();
		localSetItemQuantidade();
		mostraPortas();
		mostraTrolls();
		nomeSala.setText(mapa.getPlayer().getLocalAtual().getNome());
		frame.validate();
	}
	
	public void mostraPortas(){
		JPanelPortas.removeAll();
		for(Porta porta: mapa.getPlayer().getLocalAtual().getPortas()){
			
			ButtonPorta botaoPorta = new ButtonPorta(mapa.getPlayer().getLocalAtual(), porta);
			botaoPorta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						if(mapa.getPlayer().getPerto() instanceof Porta && mapa.getPlayer().getPerto() == porta){
                            try{
                                Comando.comando(mapa, "exit");
                            }catch (Exception e) {
                                atualizaQuantidade(quantidadePocao, Pocao.class);
                                
                                atualizaQuantidade(quantidadeOuro, Ouro.class);
								
								atualizaQuantidade(quantidadeDiamante, Diamante.class);
                                
                                atualizaGUI();
                                infoBox(e.getMessage(), "Porta");
                            }finally{
                                int quantidade = mapa.getPlayer().getItens().getQuantidadeItem(Chave.class);
                                quantidadeChaves.setText(String.valueOf(quantidade));
                                atualizaGUI();
                            }
						}else{
							setAllLabelsToDefault();
                            Comando.comando(mapa, "moveto " + porta.getFora(mapa.getPlayer().getLocalAtual()).getNome() + " door");
							botaoPorta.setText("Sair");
						}
					} catch (Exception e) {
                        atualizaGUI();
						infoBox(e.getMessage(), "Porta");
					}
				}
			});
			JPanelPortas.add(botaoPorta);
		}
	}
	
	public void mostraTrolls(){
		JPanelTrolls.removeAll();
		for(Troll troll: mapa.getPlayer().getLocalAtual().getTrolls()){
			
			JButton botaoTroll = new JButton(troll.getNome());
			botaoTroll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						Comando.comando(mapa, "throwAxe " + troll.getNome());
						JPanelTrolls.remove(botaoTroll);
						frame.validate();
                        quantidadeMachadoOuro.setText(String.valueOf(mapa.getPlayer().getItens().getQuantidadeItem(MachadoOuro.class)));
                        quantidadeMachadoBronze.setText(String.valueOf(mapa.getPlayer().getItens().getQuantidadeItem(MachadoBronze.class)));
                        quantidadeMachadoFerro.setText(String.valueOf(mapa.getPlayer().getItens().getQuantidadeItem(MachadoFerro.class)));
						
						/*TODO arrumar o label do jogador*/
					} catch (Exception e) {
						infoBox(e.getMessage(), "Porta");
					}
				}
			});
			if(troll instanceof TrollGuerreiro){
				botaoTroll.setForeground(Color.red);
			}else if(troll instanceof TrollCaverna){
				botaoTroll.setForeground(Color.blue);
			}
			JPanelTrolls.add(botaoTroll);
		}
	}
	
	// TODO - remover gambi de setar os labels
	private void setAllLabelsToDefault(){
        btnAcaoSalaOuro.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoSalaPocao.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoSalaChave.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoMachadoOuro.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoSalaDiamante.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoMachadoFerro.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        btnAcaoMachadoBronze.setEnabled(mapa.getPlayer().getLocalAtual() instanceof Sala);
        
		btnAcaoSalaOuro.setText("Mover");
		btnAcaoSalaPocao.setText("Mover");
		btnAcaoSalaChave.setText("Mover");
		btnAcaoMachadoOuro.setText("Mover");
		btnAcaoSalaDiamante.setText("Mover");
		btnAcaoMachadoFerro.setText("Mover");
		btnAcaoMachadoBronze.setText("Mover");
		for(Component botaoPorta: JPanelPortas.getComponents()){
			if(botaoPorta instanceof ButtonPorta){
				String nome = ((ButtonPorta) botaoPorta).getLabelPorta();
				((ButtonPorta) botaoPorta).setText(nome);
				
			}
		}
		frame.validate();
	}
	
	public void localSetItemQuantidade(){
		if(mapa.getPlayer().getLocalAtual() instanceof Sala){
			int quantidade;
			Sala sala = ((Sala)mapa.getPlayer().getLocalAtual());
			
			labelQuantidadeSalaOuro.setText(String.valueOf(sala.getQuantidadeOuro()));
			labelQuantidadeSalaDiamante.setText(String.valueOf(sala.getQuantidadeDiamante()));
			
			quantidade = sala.getQuantidadeItem(MachadoOuro.class);
			labelQuantidadeAxeOuro.setText(String.valueOf(quantidade));
			
			quantidade = sala.getQuantidadeItem(MachadoFerro.class);
			labelQuantidadeAxeFerro.setText(String.valueOf(quantidade));
			
			quantidade = sala.getQuantidadeItem(MachadoBronze.class);
			labelQuantidadeAxeBronze.setText(String.valueOf(quantidade));
			
			quantidade = sala.getQuantidadeItem(Pocao.class);
			labelQuantidadeSalaPocao.setText(String.valueOf(quantidade));
			
			quantidade = sala.getQuantidadeItem(Chave.class);
			labelQuantidadeSalaChave.setText(String.valueOf(quantidade));
		}else{
			labelQuantidadeAxeOuro.setText("0");			
			labelQuantidadeSalaOuro.setText("0");
			labelQuantidadeAxeFerro.setText("0");			
			labelQuantidadeAxeBronze.setText("0");			
			labelQuantidadeSalaPocao.setText("0");			
			labelQuantidadeSalaChave.setText("0");
			labelQuantidadeSalaDiamante.setText("0");
		}
	}
	
	public static void infoBox(String mensagem, String titulo){
        JOptionPane.showMessageDialog(null, mensagem, "InfoBox: " + titulo, JOptionPane.INFORMATION_MESSAGE);
    }

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        InfoSala = new javax.swing.JPanel();
        local = new javax.swing.JLabel();
        nomeSala = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnAcaoSalaOuro = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        btnAcaoSalaPocao = new javax.swing.JButton();
        labelQuantidadeSalaOuro = new javax.swing.JLabel();
        labelQuantidadeSalaPocao = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelQuantidadeAxeOuro = new javax.swing.JLabel();
        labelQuantidadeAxeBronze = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelQuantidadeAxeFerro = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        btnAcaoMachadoOuro = new javax.swing.JButton();
        btnAcaoMachadoBronze = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btnAcaoMachadoFerro = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        labelQuantidadeSalaChave = new javax.swing.JLabel();
        btnAcaoSalaChave = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        JPanelPortas = new javax.swing.JPanel();
        JPanelTrolls = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnAcaoSalaDiamante = new javax.swing.JButton();
        labelQuantidadeSalaDiamante = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        InfoJogador = new javax.swing.JPanel();
        usarPocao = new javax.swing.JButton();
        quantidadeOuro = new javax.swing.JLabel();
        quantidadeMachadoBronze = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        quantidadeDiamante = new javax.swing.JLabel();
        quantidadeMachadoFerro = new javax.swing.JLabel();
        quantidadeChaves = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        quantidadePocao = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        quantidadeMachadoOuro = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLargarMachadoOuro = new javax.swing.JButton();
        btnLargarMachadoBronze = new javax.swing.JButton();
        btnLargarMachadoFerro = new javax.swing.JButton();
        btnLargarPocao = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        local.setText("Local");

        nomeSala.setText("nome_sala");

        jLabel9.setText("Ouro");

        btnAcaoSalaOuro.setText("ação");

        jLabel10.setText("Pocao");

        btnAcaoSalaPocao.setText("ação");

        labelQuantidadeSalaOuro.setText("0");

        labelQuantidadeSalaPocao.setText("0");

        jLabel15.setText("Machados");

        jLabel16.setText("Ouro");

        labelQuantidadeAxeOuro.setText("0");

        labelQuantidadeAxeBronze.setText("0");

        jLabel19.setText("Bronze");

        labelQuantidadeAxeFerro.setText("0");

        jLabel21.setText("Ferro");

        btnAcaoMachadoOuro.setText("ação");

        btnAcaoMachadoBronze.setText("ação");

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnAcaoMachadoFerro.setText("ação");

        jLabel22.setText("Chave");

        labelQuantidadeSalaChave.setText("0");

        btnAcaoSalaChave.setText("ação");

        jLabel11.setText("Portas");

        JPanelPortas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        JPanelTrolls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel14.setText("Trolls");

        btnAcaoSalaDiamante.setText("ação");

        labelQuantidadeSalaDiamante.setText("0");

        jLabel17.setText("Diamante");

        javax.swing.GroupLayout InfoSalaLayout = new javax.swing.GroupLayout(InfoSala);
        InfoSala.setLayout(InfoSalaLayout);
        InfoSalaLayout.setHorizontalGroup(
            InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(InfoSalaLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(local)
                .addGap(6, 6, 6)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomeSala)
                    .addGroup(InfoSalaLayout.createSequentialGroup()
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelQuantidadeSalaPocao))
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelQuantidadeSalaOuro))
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelQuantidadeSalaChave))
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelQuantidadeSalaDiamante)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAcaoSalaOuro)
                            .addComponent(btnAcaoSalaPocao)
                            .addComponent(btnAcaoSalaChave)
                            .addComponent(btnAcaoSalaDiamante))))
                .addGap(32, 32, 32)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoSalaLayout.createSequentialGroup()
                        .addComponent(labelQuantidadeAxeOuro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAcaoMachadoOuro))
                    .addGroup(InfoSalaLayout.createSequentialGroup()
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelQuantidadeAxeBronze)
                            .addComponent(labelQuantidadeAxeFerro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAcaoMachadoFerro)
                            .addComponent(btnAcaoMachadoBronze))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(InfoSalaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanelTrolls, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelPortas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jSeparator4)
        );
        InfoSalaLayout.setVerticalGroup(
            InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoSalaLayout.createSequentialGroup()
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoSalaLayout.createSequentialGroup()
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(local)
                            .addComponent(jLabel15)
                            .addComponent(nomeSala))
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(labelQuantidadeSalaOuro)
                                    .addComponent(btnAcaoSalaOuro))
                                .addGap(6, 6, 6)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(labelQuantidadeSalaDiamante)
                                    .addComponent(btnAcaoSalaDiamante))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAcaoSalaPocao)
                                    .addComponent(labelQuantidadeSalaPocao)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(labelQuantidadeSalaChave)
                                    .addComponent(btnAcaoSalaChave)))
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(labelQuantidadeAxeOuro)
                                    .addComponent(btnAcaoMachadoOuro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(labelQuantidadeAxeBronze)
                                    .addComponent(btnAcaoMachadoBronze))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(labelQuantidadeAxeFerro)
                                    .addComponent(btnAcaoMachadoFerro)))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JPanelPortas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JPanelTrolls, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        usarPocao.setText("usar");
        usarPocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usarPocaoActionPerformed(evt);
            }
        });

        quantidadeOuro.setText("0");

        quantidadeMachadoBronze.setText("0");

        jLabel7.setText("Chaves:");

        jLabel6.setText("Poções");

        quantidadeDiamante.setText("0");

        quantidadeMachadoFerro.setText("0");

        quantidadeChaves.setText("0");

        jLabel3.setText("Ferro:");

        jLabel1.setText("Jogador");

        quantidadePocao.setText("0");

        jLabel4.setText("Bronze:");

        jLabel12.setText("Ouro:");

        quantidadeMachadoOuro.setText("0");

        jLabel5.setText("Ouro:");

        jLabel13.setText("Diamante:");

        jLabel2.setText("Machados:");

        btnLargarMachadoOuro.setText("Largar");

        btnLargarMachadoBronze.setText("Largar");

        btnLargarMachadoFerro.setText("Largar");

        btnLargarPocao.setText("Largar");

        javax.swing.GroupLayout InfoJogadorLayout = new javax.swing.GroupLayout(InfoJogador);
        InfoJogador.setLayout(InfoJogadorLayout);
        InfoJogadorLayout.setHorizontalGroup(
            InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoJogadorLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(InfoJogadorLayout.createSequentialGroup()
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(InfoJogadorLayout.createSequentialGroup()
                                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                                        .addComponent(quantidadeMachadoOuro)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLargarMachadoOuro))
                                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                                        .addComponent(quantidadeMachadoBronze)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLargarMachadoBronze))
                                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                                        .addComponent(quantidadeMachadoFerro)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLargarMachadoFerro))))
                            .addGroup(InfoJogadorLayout.createSequentialGroup()
                                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(quantidadeChaves)
                                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                                        .addComponent(quantidadePocao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(usarPocao)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnLargarPocao))))))
                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantidadeOuro))
                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantidadeDiamante)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        InfoJogadorLayout.setVerticalGroup(
            InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoJogadorLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InfoJogadorLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(23, 23, 23))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantidadeMachadoOuro)
                        .addComponent(jLabel5)
                        .addComponent(btnLargarMachadoOuro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(quantidadeMachadoBronze)
                    .addComponent(btnLargarMachadoBronze))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantidadeMachadoFerro)
                    .addComponent(btnLargarMachadoFerro))
                .addGap(18, 18, 18)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(quantidadePocao)
                    .addComponent(usarPocao)
                    .addComponent(btnLargarPocao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(quantidadeChaves))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(quantidadeOuro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(quantidadeDiamante))
                .addContainerGap())
        );

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(InfoSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InfoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(InfoJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents
		
    private void usarPocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usarPocaoActionPerformed
		try {
			Comando.comando(mapa, "lock");
			atualizaGUI();
            int quantidade = mapa.getPlayer().getItens().getQuantidadeItem(Pocao.class);
            quantidadePocao.setText(String.valueOf(quantidade));
		} catch (Exception ex) {
			infoBox(ex.getMessage(), "Poção");
		}
    }//GEN-LAST:event_usarPocaoActionPerformed

    public void atualizaQuantidade(JLabel labelQuantidade, Class classe){
        int quantidade = mapa.getPlayer().getItens().getQuantidadeItem(classe);
        labelQuantidade.setText(String.valueOf(quantidade));
    }
    
    public ActionListener acaoBotaoMover(String tipo, JLabel labelQuantidade, Class classe){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(mapa.getPlayer().getPerto() != null && mapa.getPlayer().getPerto().getClass().equals(classe)){
                if(((JButton)e.getSource()).getText().equals("Pegar")){
                    try{
                        Comando.comando(mapa, "pickup " + tipo);
                        atualizaQuantidade(labelQuantidade, classe);
                        localSetItemQuantidade();
                        setAllLabelsToDefault();
                    }catch(Exception ex){
                        infoBox(ex.getMessage(), "Pegar " + tipo);
                    }
                }else{
                    try{
                        Comando.comando(mapa, "moveto " + tipo);
                        setAllLabelsToDefault();
                        ((JButton)e.getSource()).setText("Pegar");
                    } catch (Exception ex) {
                        infoBox(ex.getMessage(), "Mover para " + tipo);
                    }
                }
            }
        };
    }
    
    public ActionListener acaoBotaoLargar(String tipo, JLabel labelQuantidade, Class classe){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Comando.comando(mapa, "drop " + tipo);
                    atualizaQuantidade(labelQuantidade, classe);
                }catch(Exception ex){
                    infoBox(ex.getMessage(), "Largar " + tipo);
                }
            }
        };
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InfoJogador;
    private javax.swing.JPanel InfoSala;
    private javax.swing.JPanel JPanelPortas;
    private javax.swing.JPanel JPanelTrolls;
    private javax.swing.JButton btnAcaoMachadoBronze;
    private javax.swing.JButton btnAcaoMachadoFerro;
    private javax.swing.JButton btnAcaoMachadoOuro;
    private javax.swing.JButton btnAcaoSalaChave;
    private javax.swing.JButton btnAcaoSalaDiamante;
    private javax.swing.JButton btnAcaoSalaOuro;
    private javax.swing.JButton btnAcaoSalaPocao;
    private javax.swing.JButton btnLargarMachadoBronze;
    private javax.swing.JButton btnLargarMachadoFerro;
    private javax.swing.JButton btnLargarMachadoOuro;
    private javax.swing.JButton btnLargarPocao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel labelQuantidadeAxeBronze;
    private javax.swing.JLabel labelQuantidadeAxeFerro;
    private javax.swing.JLabel labelQuantidadeAxeOuro;
    private javax.swing.JLabel labelQuantidadeSalaChave;
    private javax.swing.JLabel labelQuantidadeSalaDiamante;
    private javax.swing.JLabel labelQuantidadeSalaOuro;
    private javax.swing.JLabel labelQuantidadeSalaPocao;
    private javax.swing.JLabel local;
    private javax.swing.JLabel nomeSala;
    private javax.swing.JLabel quantidadeChaves;
    private javax.swing.JLabel quantidadeDiamante;
    private javax.swing.JLabel quantidadeMachadoBronze;
    private javax.swing.JLabel quantidadeMachadoFerro;
    private javax.swing.JLabel quantidadeMachadoOuro;
    private javax.swing.JLabel quantidadeOuro;
    private javax.swing.JLabel quantidadePocao;
    private javax.swing.JButton usarPocao;
    // End of variables declaration//GEN-END:variables
}
