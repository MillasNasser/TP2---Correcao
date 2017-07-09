/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Default.Chave;
import Default.Console;
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
		atualizaGUI();
	}
	
	private void atualizaGUI(){
		setAllLabelsToDefault();
		defineQuantidadeItens();
		mostraPortas();
		mostraTrolls();
		nomeSala.setText(mapa.getPlayer().getLocalAtual().getNome());
		frame.validate();
	}
	
	public void mostraPortas(){
		JPanelPortas.removeAll();
		for(Porta porta: mapa.getPlayer().getLocalAtual().getPortas()){
			
			ButtonPorta botaoPorta = new ButtonPorta(porta);
			botaoPorta.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent event) {
					try {
						if(mapa.getPlayer().getPerto() instanceof Porta && mapa.getPlayer().getPerto()==porta){
                            Console.console(mapa, "exit");
							int quantidadeChave = -1;
							quantidadeChaves.setText((quantidadeChave)+"");
							atualizaGUI();
						}else{
							setAllLabelsToDefault();
                            Console.console(mapa, "moveto "+porta.getIdentificador()+" door");
							botaoPorta.setLabel("sair");
						}
					} catch (Exception e) {
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
						Console.console(mapa, "throwAxe "+troll.getNome());
						JPanelTrolls.remove(botaoTroll);
						frame.validate();
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
		bntAcaoSalaOuro.setLabel("Mover");
		bntAcaoSalaPocao.setLabel("Mover");
		bntAcaoSalaChave.setLabel("Mover");
		bntAcaoMachadoOuro.setLabel("Mover");
		bntAcaoMachadoFerro.setLabel("Mover");
		bntAcaoMachadoBronze.setLabel("Mover");
		for(Component botaoPorta: JPanelPortas.getComponents()){
			if(botaoPorta instanceof ButtonPorta){
				String nome = ((ButtonPorta) botaoPorta).getLabelPorta();
				((ButtonPorta) botaoPorta).setLabel(nome);
				
			}
		}
		frame.validate();
	}
	
	public void defineQuantidadeItens(){
		if(mapa.getPlayer().getLocalAtual() instanceof Sala){
			int quantidade;
			Sala sala = ((Sala)mapa.getPlayer().getLocalAtual());
			
			labelQuantidadeSalaOuro.setText(sala.getQuantidadeOuro()+"");
			
			quantidade = sala.getQuantidadeItem(MachadoOuro.class);
			labelQuantidadeAxeOuro.setText(quantidade+"");
			
			quantidade = sala.getQuantidadeItem(MachadoFerro.class);
			labelQuantidadeAxeFerro.setText(quantidade+"");
			
			quantidade = sala.getQuantidadeItem(MachadoBronze.class);
			labelQuantidadeAxeBronze.setText(quantidade+"");
			
			quantidade = sala.getQuantidadeItem(Pocao.class);
			labelQuantidadeSalaPocao.setText(quantidade+"");
			
			quantidade = sala.getQuantidadeItem(Chave.class);
			labelQuantidadeSalaChave.setText(quantidade+"");
		}else{
			labelQuantidadeAxeOuro.setText("0");			
			labelQuantidadeSalaOuro.setText("0");
			labelQuantidadeAxeFerro.setText("0");			
			labelQuantidadeAxeBronze.setText("0");			
			labelQuantidadeSalaPocao.setText("0");			
			labelQuantidadeSalaChave.setText("0");
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
        bntAcaoSalaOuro = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        bntAcaoSalaPocao = new javax.swing.JButton();
        labelQuantidadeSalaOuro = new javax.swing.JLabel();
        labelQuantidadeSalaPocao = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        labelQuantidadeAxeOuro = new javax.swing.JLabel();
        labelQuantidadeAxeBronze = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        labelQuantidadeAxeFerro = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        bntAcaoMachadoOuro = new javax.swing.JButton();
        bntAcaoMachadoBronze = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        bntAcaoMachadoFerro = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        labelQuantidadeSalaChave = new javax.swing.JLabel();
        bntAcaoSalaChave = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        JPanelPortas = new javax.swing.JPanel();
        JPanelTrolls = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
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
        jSeparator1 = new javax.swing.JSeparator();

        local.setText("Local");

        nomeSala.setText("nome_sala");

        jLabel9.setText("Ouro");

        bntAcaoSalaOuro.setText("ação");
        bntAcaoSalaOuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoSalaOuroActionPerformed(evt);
            }
        });

        jLabel10.setText("Pocao");

        bntAcaoSalaPocao.setText("ação");
        bntAcaoSalaPocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoSalaPocaoActionPerformed(evt);
            }
        });

        labelQuantidadeSalaOuro.setText("0");

        labelQuantidadeSalaPocao.setText("0");

        jLabel15.setText("Machados");

        jLabel16.setText("ouro");

        labelQuantidadeAxeOuro.setText("0");

        labelQuantidadeAxeBronze.setText("0");

        jLabel19.setText("bronze");

        labelQuantidadeAxeFerro.setText("0");

        jLabel21.setText("ferro");

        bntAcaoMachadoOuro.setText("ação");
        bntAcaoMachadoOuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoMachadoOuroActionPerformed(evt);
            }
        });

        bntAcaoMachadoBronze.setText("ação");
        bntAcaoMachadoBronze.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoMachadoBronzeActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        bntAcaoMachadoFerro.setText("ação");
        bntAcaoMachadoFerro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoMachadoFerroActionPerformed(evt);
            }
        });

        jLabel22.setText("Chave");

        labelQuantidadeSalaChave.setText("0");

        bntAcaoSalaChave.setText("ação");
        bntAcaoSalaChave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntAcaoSalaChaveActionPerformed(evt);
            }
        });

        jLabel11.setText("Portas");

        JPanelPortas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        JPanelTrolls.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel14.setText("Trolls");

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
                                .addComponent(labelQuantidadeSalaChave)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntAcaoSalaOuro)
                            .addComponent(bntAcaoSalaPocao)
                            .addComponent(bntAcaoSalaChave))))
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
                        .addComponent(bntAcaoMachadoOuro))
                    .addGroup(InfoSalaLayout.createSequentialGroup()
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelQuantidadeAxeBronze)
                            .addComponent(labelQuantidadeAxeFerro))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bntAcaoMachadoFerro)
                            .addComponent(bntAcaoMachadoBronze))))
                .addContainerGap(240, Short.MAX_VALUE))
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
                                    .addComponent(bntAcaoSalaOuro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bntAcaoSalaPocao)
                                    .addComponent(labelQuantidadeSalaPocao)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(labelQuantidadeSalaChave)
                                    .addComponent(bntAcaoSalaChave)))
                            .addGroup(InfoSalaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(labelQuantidadeAxeOuro)
                                    .addComponent(bntAcaoMachadoOuro))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(labelQuantidadeAxeBronze)
                                    .addComponent(bntAcaoMachadoBronze))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(InfoSalaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(labelQuantidadeAxeFerro)
                                    .addComponent(bntAcaoMachadoFerro)))))
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

        jLabel7.setText("Chave:");

        jLabel6.setText("Poção:");

        quantidadeDiamante.setText("0");

        quantidadeMachadoFerro.setText("0");

        quantidadeChaves.setText("0");

        jLabel3.setText("ferro:");

        jLabel1.setText("Jogador");

        quantidadePocao.setText("0");

        jLabel4.setText("bronze:");

        jLabel12.setText("Ouro:");

        quantidadeMachadoOuro.setText("0");

        jLabel5.setText("ouro:");

        jLabel13.setText("Diamante:");

        jLabel2.setText("Machados:");

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
                                    .addComponent(quantidadeMachadoOuro)
                                    .addComponent(quantidadeMachadoBronze)
                                    .addComponent(quantidadeMachadoFerro)))
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
                                        .addComponent(usarPocao))))))
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
                .addContainerGap(38, Short.MAX_VALUE))
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
                        .addComponent(jLabel5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(quantidadeMachadoBronze))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantidadeMachadoFerro))
                .addGap(18, 18, 18)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(quantidadePocao)
                    .addComponent(usarPocao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(InfoJogadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(quantidadeChaves))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
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
                .addComponent(InfoJogador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InfoSala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(InfoJogador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents
	
    private void bntAcaoSalaOuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoSalaOuroActionPerformed
        setAllLabelsToDefault();
		try {
			int quantidadeDeOuro;
			if(mapa.getPlayer().getPerto() instanceof Ouro){
				int quantidade = Integer.parseInt(quantidadeMachadoOuro.getText());
				quantidadeDeOuro = ((Ouro) mapa.getPlayer().getPerto()).getQuantidade();
				Console.console(mapa, "pickup ouro");
				quantidadeOuro.setText((quantidade+quantidadeDeOuro)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto ouro");
				quantidadeDeOuro = ((Ouro) mapa.getPlayer().getPerto()).getQuantidade();
				bntAcaoSalaOuro.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Ouro");
		}
    }//GEN-LAST:event_bntAcaoSalaOuroActionPerformed
	
    private void usarPocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usarPocaoActionPerformed
		int qntPocao = Integer.parseInt(quantidadePocao.getText());
		quantidadePocao.setText((qntPocao-1)+"");
		try {
			Console.console(mapa, "lock");
			atualizaGUI();
		} catch (Exception ex) {
			infoBox(ex.getMessage(), "Poção");
		}
    }//GEN-LAST:event_usarPocaoActionPerformed

    private void bntAcaoMachadoOuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoMachadoOuroActionPerformed
		setAllLabelsToDefault();
		try {
			if(mapa.getPlayer().getPerto() instanceof MachadoOuro){
				Console.console(mapa, "pickup machado de ouro");
				int quantidade = Integer.parseInt(quantidadeMachadoOuro.getText());
				quantidadeMachadoOuro.setText((quantidade+1)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto machado de ouro");
				bntAcaoMachadoOuro.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Machado de Ouro");
		}
    }//GEN-LAST:event_bntAcaoMachadoOuroActionPerformed

    private void bntAcaoMachadoBronzeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoMachadoBronzeActionPerformed
		setAllLabelsToDefault();
		try {
			if(mapa.getPlayer().getPerto() instanceof MachadoBronze){
				Console.console(mapa, "pickup machado de bronze");
				int quantidade = Integer.parseInt(quantidadeMachadoBronze.getText());
				quantidadeMachadoBronze.setText((quantidade+1)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto machado de bronze");
				bntAcaoMachadoBronze.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Machado de Ouro");
		}
    }//GEN-LAST:event_bntAcaoMachadoBronzeActionPerformed

    private void bntAcaoMachadoFerroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoMachadoFerroActionPerformed
        setAllLabelsToDefault();
		try {
			if(mapa.getPlayer().getPerto() instanceof MachadoFerro){
				Console.console(mapa, "pickup machado de Ferro");
				int quantidade = Integer.parseInt(quantidadeMachadoFerro.getText());
				quantidadeMachadoFerro.setText((quantidade+1)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto machado de Ferro");
				bntAcaoMachadoFerro.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Machado de Ouro");
		}
    }//GEN-LAST:event_bntAcaoMachadoFerroActionPerformed

    private void bntAcaoSalaPocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoSalaPocaoActionPerformed
        setAllLabelsToDefault();
		try {
			if(mapa.getPlayer().getPerto() instanceof Pocao){
				Console.console(mapa, "pickup pocao");
				int quantidade = Integer.parseInt(quantidadePocao.getText());
				quantidadePocao.setText((quantidade+1)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto pocao");
				bntAcaoSalaPocao.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Poção");
		}
    }//GEN-LAST:event_bntAcaoSalaPocaoActionPerformed

    private void bntAcaoSalaChaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntAcaoSalaChaveActionPerformed
        setAllLabelsToDefault();
		try {
			if(mapa.getPlayer().getPerto() instanceof Chave){
				Console.console(mapa, "pickup Chave");
				int quantidade = Integer.parseInt(quantidadeChaves.getText());
				quantidadeChaves.setText((quantidade+1)+"");
				defineQuantidadeItens();
			}else{
				Console.console(mapa, "moveto Chave");
				bntAcaoSalaChave.setLabel("pegar");
			}
		} catch (Exception e) {
			infoBox(e.getMessage(), "Poção");
		}
    }//GEN-LAST:event_bntAcaoSalaChaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel InfoJogador;
    private javax.swing.JPanel InfoSala;
    private javax.swing.JPanel JPanelPortas;
    private javax.swing.JPanel JPanelTrolls;
    private javax.swing.JButton bntAcaoMachadoBronze;
    private javax.swing.JButton bntAcaoMachadoFerro;
    private javax.swing.JButton bntAcaoMachadoOuro;
    private javax.swing.JButton bntAcaoSalaChave;
    private javax.swing.JButton bntAcaoSalaOuro;
    private javax.swing.JButton bntAcaoSalaPocao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
