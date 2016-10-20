/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms;

import dao.AlunoDAO;
import dao.GrupoDAO;
import dao.LancamentoDAO;
import dao.TurmaDAO;
import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalTime;
import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import modelo.Aluno;
import modelo.Grupo;
import modelo.Lancamento;
import modelo.Turma;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author manolopina
 */
public class MainForm extends javax.swing.JFrame {
    
    TurmaDAO turmaDao = new TurmaDAO();
    GrupoDAO grupoDao  = new GrupoDAO();
    AlunoDAO alunoDao = new AlunoDAO();
    LancamentoDAO lancamentoDao = new LancamentoDAO();
    String turmaSelecionada;
    String grupoSelecionado;
    String lancamentoGrupoSelecionado;
    int rmSelecionado;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        
        initComponents();
        
        this.tableTurma.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                turmaSelecionada = tableTurma.getValueAt(tableTurma.getSelectedRow(), 0).toString();
                txtTurma.setText(tableTurma.getValueAt(tableTurma.getSelectedRow(), 0).toString());
            }
        });
        
        this.tableGrupo.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtGrupoNome.setText(tableGrupo.getValueAt(tableGrupo.getSelectedRow(), 0).toString());
                grupoSelecionado = tableGrupo.getValueAt(tableGrupo.getSelectedRow(), 0).toString();
            }
        });
        
        this.tableAluno.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtAlunoNome.setText(tableAluno.getValueAt(tableAluno.getSelectedRow(), 0).toString());
                txtRMAluno.setText(tableAluno.getValueAt(tableAluno.getSelectedRow(), 1).toString());
                rmSelecionado = Integer.parseInt(tableAluno.getValueAt(tableAluno.getSelectedRow(), 1).toString());
                selectGrupoCombo.setSelectedItem(tableAluno.getValueAt(tableAluno.getSelectedRow(), 2).toString());
                selectTurma.setSelectedItem(tableAluno.getValueAt(tableAluno.getSelectedRow(), 3).toString());
            }
        });
        
        this.tableLancamento.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                txtAltitudeMedia.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 0).toString());
                txtVelocidadeMaxima.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 1).toString());
                txtTaxaDescida.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 2).toString());
                txtTempoApogeuDescida.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 3).toString());
                txtPicoAceleracao.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 4).toString());
                txtAceleracaoMedia.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 5).toString());
                txtTempoPropulsao.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 6).toString());
                txtTempoEjecao.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 7).toString());
                txtAltitudeEjecao.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 8).toString());
                txtDuracao.setText(tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 9).toString());
                lancamentoGrupoSelecionado = tableLancamento.getValueAt(tableLancamento.getSelectedRow(), 10).toString();
            }
        });
        
        
        
        this.updateComboBoxes();
        this.updateTables();
    }
    
    
    /*
    Load table
    */
    public void updateTableTurma() {
        this.tableTurma.setModel(DbUtils.resultSetToTableModel(this.turmaDao.listarTabela()));
    }
    
    public void updateTableGrupo() {
        int i = 0;
        for(Grupo grupo : this.grupoDao.listar()) {
            this.tableGrupo.getModel().setValueAt(grupo.getNome(), i, 0);
            i++;
        }
    }
    
    public void updateTableGrupo(String termo) {
        int i = 0;
        for(Grupo grupo : this.grupoDao.pesquisar(new Grupo(termo))) {
            this.tableGrupo.getModel().setValueAt(grupo.getNome(), i, 0);
            i++;
        }
    }
    
    public void updateTableAluno() {
        int i = 0;
        for(Aluno aluno : this.alunoDao.listar()) {
            this.tableAluno.getModel().setValueAt(aluno.getAlunoNome(), i, 0);
            this.tableAluno.getModel().setValueAt(aluno.getRM().toString(), i, 1);
            this.tableAluno.getModel().setValueAt(aluno.getTurmaNome(), i, 2);
            this.tableAluno.getModel().setValueAt(aluno.getGrupoNome(), i, 3);this.tableAluno.getModel().setValueAt(aluno.getAlunoNome(), i, 0);
            i++;
        }
    }
    
    public void updateTableLancamento() {
        int i = 0;
        for(Lancamento lancamento : this.lancamentoDao.listar()) {
            this.tableLancamento.getModel().setValueAt(lancamento.getAltitudeMedia(), i, 0);
            this.tableLancamento.getModel().setValueAt(lancamento.getVelocidadeMaxima(), i, 1);
            this.tableLancamento.getModel().setValueAt(lancamento.getTaxaDescida(), i, 2);
            this.tableLancamento.getModel().setValueAt(lancamento.getTempoApogeuDescida(), i, 3);
            this.tableLancamento.getModel().setValueAt(lancamento.getPicoAceleracao(), i, 4);
            this.tableLancamento.getModel().setValueAt(lancamento.getAceleracaoMedia(), i, 5);
            this.tableLancamento.getModel().setValueAt(lancamento.getTempoPropulsao(), i, 6);
            this.tableLancamento.getModel().setValueAt(lancamento.getTempoEjecao(), i, 7);
            this.tableLancamento.getModel().setValueAt(lancamento.getAltitudeEjecao(), i, 8);
            this.tableLancamento.getModel().setValueAt(lancamento.getDuracaoVoo(), i, 9);
            this.tableLancamento.getModel().setValueAt(lancamento.getGrupoNome(), i, 10);
            i++;
        }
    }
    
    //Population select comboboxes
    public void updateSelectTurma() {
        int i = 0;
        for(Turma turma : this.turmaDao.listar()) {
            this.selectTurma.addItem(turma.getNome());
        }
    }
    
    public void updateSelectGrupo() {
        int i = 0;
        for(Grupo grupo : this.grupoDao.listar()) {
            this.selectGrupoCombo.addItem(grupo.getNome());
        }
    }
    
    public void updateSelectGrupoLancamento() {
        int i = 0;
        for(Grupo grupo : this.grupoDao.listar()) {
            this.selectLancamentoGrupo.addItem(grupo.getNome());
        }
    }
    
    
    public void updateComboBoxes() {
        this.updateSelectTurma();
        this.updateSelectGrupo();
        this.updateSelectGrupoLancamento();
    }
    
    public void updateTables() {
        this.updateTableTurma();
        this.updateTableGrupo();
        this.updateTableAluno();
        this.updateTableLancamento();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTurma = new javax.swing.JTextField();
        btnCadastrarTurma = new javax.swing.JButton();
        btnPesquisarTurma = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableTurma = new javax.swing.JTable();
        btnAlterarTurma = new javax.swing.JButton();
        btnExcluirTurma = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtGrupoNome = new javax.swing.JTextField();
        btnCadastrarGrupo = new javax.swing.JButton();
        btnPesquisarGrupo = new javax.swing.JButton();
        btnAlterarGrupo = new javax.swing.JButton();
        btnExcluirGrupo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableGrupo = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtAlunoNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        selectTurma = new javax.swing.JComboBox<>();
        btnCadastrarAluno = new javax.swing.JButton();
        btnPesquisarAluno = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        selectGrupoCombo = new javax.swing.JComboBox<>();
        btnAlterarAluno = new javax.swing.JButton();
        btnExcluirAluno = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtRMAluno = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAluno = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtAltitudeMedia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTaxaDescida = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTempoApogeuDescida = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtAceleracaoMedia = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTempoPropulsao = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtTempoEjecao = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtAltitudeEjecao = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtDuracao = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        selectLancamentoGrupo = new javax.swing.JComboBox<>();
        btnCadastrarLancamento = new javax.swing.JButton();
        btnPesquisarLancamento = new javax.swing.JButton();
        btnExcluirLancamento = new javax.swing.JButton();
        btnAlterarLancamento = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtVelocidadeMaxima = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtPicoAceleracao = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableLancamento = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome da turma");

        txtTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTurmaActionPerformed(evt);
            }
        });

        btnCadastrarTurma.setText("Cadastrar");
        btnCadastrarTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarTurmaActionPerformed(evt);
            }
        });

        btnPesquisarTurma.setText("Pesquisar");
        btnPesquisarTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarTurmaActionPerformed(evt);
            }
        });

        tableTurma.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "Editar", "Excluir"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableTurma);
        if (tableTurma.getColumnModel().getColumnCount() > 0) {
            tableTurma.getColumnModel().getColumn(0).setResizable(false);
            tableTurma.getColumnModel().getColumn(0).setHeaderValue("Nome");
            tableTurma.getColumnModel().getColumn(1).setResizable(false);
            tableTurma.getColumnModel().getColumn(1).setHeaderValue("Editar");
            tableTurma.getColumnModel().getColumn(2).setResizable(false);
            tableTurma.getColumnModel().getColumn(2).setHeaderValue("Excluir");
        }

        btnAlterarTurma.setText("Alterar");
        btnAlterarTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarTurmaActionPerformed(evt);
            }
        });

        btnExcluirTurma.setText("Excluir");
        btnExcluirTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirTurmaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtTurma))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnCadastrarTurma)
                            .addGap(18, 18, 18)
                            .addComponent(btnAlterarTurma)
                            .addGap(18, 18, 18)
                            .addComponent(btnExcluirTurma)
                            .addGap(18, 18, 18)
                            .addComponent(btnPesquisarTurma))))
                .addContainerGap(231, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarTurma)
                    .addComponent(btnPesquisarTurma)
                    .addComponent(btnAlterarTurma)
                    .addComponent(btnExcluirTurma))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(557, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Turma", jPanel1);

        jLabel2.setText("Nome do grupo");

        btnCadastrarGrupo.setText("Cadastrar");
        btnCadastrarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarGrupoActionPerformed(evt);
            }
        });

        btnPesquisarGrupo.setText("Pesquisar");
        btnPesquisarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarGrupoActionPerformed(evt);
            }
        });

        btnAlterarGrupo.setText("Alterar");
        btnAlterarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarGrupoActionPerformed(evt);
            }
        });

        btnExcluirGrupo.setText("Excluir");
        btnExcluirGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirGrupoActionPerformed(evt);
            }
        });

        tableGrupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableGrupo);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtGrupoNome))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCadastrarGrupo)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarGrupo)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirGrupo)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisarGrupo)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtGrupoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarGrupo)
                    .addComponent(btnAlterarGrupo)
                    .addComponent(btnPesquisarGrupo)
                    .addComponent(btnExcluirGrupo))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(398, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Grupo", jPanel2);

        jLabel4.setText("Nome do aluno:");

        jLabel5.setText("Selecionar turma :");

        selectTurma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não existe nenhuma turma cadastrada" }));
        selectTurma.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                selectTurmaComponentRemoved(evt);
            }
        });
        selectTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectTurmaActionPerformed(evt);
            }
        });

        btnCadastrarAluno.setText("Cadastrar");
        btnCadastrarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarAlunoActionPerformed(evt);
            }
        });

        btnPesquisarAluno.setText("Pesquisar");
        btnPesquisarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarAlunoActionPerformed(evt);
            }
        });

        jLabel6.setText("Selecionar grupo");

        selectGrupoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Não existe nenhuma turma cadastrada" }));
        selectGrupoCombo.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                selectGrupoComboComponentRemoved(evt);
            }
        });

        btnAlterarAluno.setText("Alterar");
        btnAlterarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarAlunoActionPerformed(evt);
            }
        });

        btnExcluirAluno.setText("Excluir");
        btnExcluirAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirAlunoActionPerformed(evt);
            }
        });

        jLabel17.setText("RM Aluno");

        tableAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Aluno", "RM", "Turma", "Grupo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableAluno);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtAlunoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addGap(18, 18, 18)
                                            .addComponent(selectGrupoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtRMAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(selectTurma, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnCadastrarAluno)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarAluno)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirAluno)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisarAluno)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtAlunoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtRMAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(selectTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(selectGrupoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarAluno)
                    .addComponent(btnPesquisarAluno)
                    .addComponent(btnAlterarAluno)
                    .addComponent(btnExcluirAluno))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Aluno", jPanel3);

        jLabel3.setText("Dados de lançamento:");

        jLabel7.setText("Altitude média");

        txtAltitudeMedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAltitudeMediaActionPerformed(evt);
            }
        });

        jLabel9.setText("Taxa de descida");

        txtTaxaDescida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaxaDescidaActionPerformed(evt);
            }
        });

        jLabel10.setText("Tempo de apogeu de descida");

        txtTempoApogeuDescida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoApogeuDescidaActionPerformed(evt);
            }
        });

        jLabel11.setText("Aceleração média");

        txtAceleracaoMedia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAceleracaoMediaActionPerformed(evt);
            }
        });

        jLabel12.setText("Tempo de propulsão");

        txtTempoPropulsao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoPropulsaoActionPerformed(evt);
            }
        });

        jLabel13.setText("Tempo de ejeção");

        txtTempoEjecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTempoEjecaoActionPerformed(evt);
            }
        });

        jLabel14.setText("Altitude de ejeção");

        txtAltitudeEjecao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAltitudeEjecaoActionPerformed(evt);
            }
        });

        jLabel15.setText("Duração");

        txtDuracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDuracaoActionPerformed(evt);
            }
        });

        jLabel16.setText("Lançamento pelo grupo:");

        selectLancamentoGrupo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecionar..." }));

        btnCadastrarLancamento.setText("Cadastrar");
        btnCadastrarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarLancamentoActionPerformed(evt);
            }
        });

        btnPesquisarLancamento.setText("Pesquisar");

        btnExcluirLancamento.setText("Excluir");
        btnExcluirLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirLancamentoActionPerformed(evt);
            }
        });

        btnAlterarLancamento.setText("Alterar");
        btnAlterarLancamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarLancamentoActionPerformed(evt);
            }
        });

        jLabel18.setText("Velocidade maxima");

        txtVelocidadeMaxima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVelocidadeMaximaActionPerformed(evt);
            }
        });

        jLabel20.setText("Pico aceleração");

        txtPicoAceleracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPicoAceleracaoActionPerformed(evt);
            }
        });

        tableLancamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Altitude Média", "Velocidade Máxima", "Taxa Descida", "Tempo Apogeu", "Pico Aceleração", "Aceleração Média", "Tempo Propulsão", "Tempo ejeção", "Altitude Ejeção", "Duração", "Grupo Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tableLancamento);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel13)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(212, 212, 212)
                                .addComponent(txtTempoEjecao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnCadastrarLancamento)
                                .addGap(18, 18, 18)
                                .addComponent(btnAlterarLancamento)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluirLancamento)
                                .addGap(18, 18, 18)
                                .addComponent(btnPesquisarLancamento))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtAceleracaoMedia, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                            .addComponent(jLabel9)
                                            .addGap(111, 111, 111)
                                            .addComponent(txtTaxaDescida, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18))
                                        .addGap(60, 60, 60)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtVelocidadeMaxima)
                                            .addComponent(txtAltitudeMedia, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(selectLancamentoGrupo, javax.swing.GroupLayout.Alignment.LEADING, 0, 214, Short.MAX_VALUE)))
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(29, 29, 29)
                                                .addComponent(txtTempoApogeuDescida))
                                            .addComponent(jLabel12)
                                            .addGroup(jPanel4Layout.createSequentialGroup()
                                                .addGap(212, 212, 212)
                                                .addComponent(txtTempoPropulsao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel20)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addGap(212, 212, 212)
                                            .addComponent(txtPicoAceleracao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(212, 212, 212)
                                        .addComponent(txtAltitudeEjecao, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(103, 103, 103))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(selectLancamentoGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtAltitudeMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtVelocidadeMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTaxaDescida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtAceleracaoMedia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTempoApogeuDescida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtTempoPropulsao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtAltitudeEjecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtPicoAceleracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTempoEjecao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtDuracao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarLancamento)
                    .addComponent(btnPesquisarLancamento)
                    .addComponent(btnAlterarLancamento)
                    .addComponent(btnExcluirLancamento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("Dados de lançamento", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 973, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jTabbedPane6.getAccessibleContext().setAccessibleName("Dados Lançamento");
        jTabbedPane6.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTurmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTurmaActionPerformed

    private void txtAltitudeMediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAltitudeMediaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAltitudeMediaActionPerformed

    private void txtTaxaDescidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaxaDescidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTaxaDescidaActionPerformed

    private void txtTempoApogeuDescidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoApogeuDescidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempoApogeuDescidaActionPerformed

    private void txtAceleracaoMediaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAceleracaoMediaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAceleracaoMediaActionPerformed

    private void txtTempoPropulsaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoPropulsaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempoPropulsaoActionPerformed

    private void txtTempoEjecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTempoEjecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTempoEjecaoActionPerformed

    private void txtAltitudeEjecaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAltitudeEjecaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAltitudeEjecaoActionPerformed

    private void txtDuracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDuracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDuracaoActionPerformed

    private void btnCadastrarTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarTurmaActionPerformed
        // TODO add your handling code here:
        String turmaNome = txtTurma.getText();
        Turma turma = new Turma(turmaNome);
        TurmaDAO turmaDAO = new TurmaDAO();
        turmaDAO.inserir(turma);
        this.updateComboBoxes();
        this.updateTables();
    }//GEN-LAST:event_btnCadastrarTurmaActionPerformed

    private void btnAlterarTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarTurmaActionPerformed
        // TODO add your handling code here:
        this.turmaDao.alterarTurma(new Turma(this.turmaSelecionada), new Turma(txtTurma.getText()));
        JOptionPane.showMessageDialog(null, "Turma alterada com sucesso!");
        this.updateTableTurma();
    }//GEN-LAST:event_btnAlterarTurmaActionPerformed

    private void btnPesquisarTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarTurmaActionPerformed
        // TODO add your handling code here:
        String termo = this.txtTurma.getText();
        this.tableTurma.setModel(DbUtils.resultSetToTableModel(this.turmaDao.pesquisarTable(termo)));
    }//GEN-LAST:event_btnPesquisarTurmaActionPerformed

    private void btnExcluirTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirTurmaActionPerformed
        // TODO add your handling code here:
        Turma turma = new Turma(txtTurma.getText());
        this.turmaDao.deletar(turma);
        JOptionPane.showMessageDialog(null, "Turma excluída");
        this.updateTableTurma();
    }//GEN-LAST:event_btnExcluirTurmaActionPerformed

    private void btnAlterarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarGrupoActionPerformed
        // TODO add your handling code here:
        Grupo grupoAlterado = new Grupo(this.txtGrupoNome.getText());
        Grupo grupoSelecionado = new Grupo(this.grupoSelecionado);
        this.grupoDao.alterar(grupoAlterado, grupoSelecionado);
        this.updateTables();
    }//GEN-LAST:event_btnAlterarGrupoActionPerformed

    private void btnExcluirGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirGrupoActionPerformed
        // TODO add your handling code here:
        Grupo grupo = new Grupo(txtGrupoNome.getText());
        this.grupoDao.deletar(grupo);
        JOptionPane.showMessageDialog(null, "Grupo excluído");
        this.updateTables();
    }//GEN-LAST:event_btnExcluirGrupoActionPerformed

    private void selectGrupoComboComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_selectGrupoComboComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_selectGrupoComboComponentRemoved

    private void selectTurmaComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_selectTurmaComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTurmaComponentRemoved

    private void btnCadastrarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarGrupoActionPerformed
        // TODO add your handling code here:
        System.out.println(txtGrupoNome.getText());
        Grupo grupo = new Grupo(txtGrupoNome.getText());
        this.grupoDao.cadastrar(grupo);
        this.updateTables();
        this.updateComboBoxes();
    }//GEN-LAST:event_btnCadastrarGrupoActionPerformed

    private void btnPesquisarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarGrupoActionPerformed
        // TODO add your handling code here:
        String termo = this.txtGrupoNome.getText();
        this.tableGrupo.clearSelection();
        this.updateTableGrupo(termo);
    }//GEN-LAST:event_btnPesquisarGrupoActionPerformed

    private void selectTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectTurmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectTurmaActionPerformed

    private void btnCadastrarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarAlunoActionPerformed
        // TODO add your handling code here: 
        try {
            this.alunoDao.inserir(new Aluno(
                    this.selectTurma.getSelectedItem().toString(), 
                    txtAlunoNome.getText(), 
                    Integer.parseInt(txtRMAluno.getText()), 
                    this.selectGrupoCombo.getSelectedItem().toString())
            );
            JOptionPane.showMessageDialog(null, "Aluno cadastrado!");
            this.updateTables();
            this.updateComboBoxes();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnCadastrarAlunoActionPerformed

    private void btnAlterarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarAlunoActionPerformed
        // TODO add your handling code here:
        try {
            this.alunoDao.alterar(new Aluno(
                    this.selectTurma.getSelectedItem().toString(),
                    this.txtAlunoNome.getText(),
                    Integer.parseInt(this.txtRMAluno.getText()),
                    this.selectGrupoCombo.getSelectedItem().toString()
            ), this.rmSelecionado);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.updateTables();
    }//GEN-LAST:event_btnAlterarAlunoActionPerformed

    private void btnExcluirAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirAlunoActionPerformed
        // TODO add your handling code here:
        try {
            this.alunoDao.deletar(this.rmSelecionado);    
            this.updateComboBoxes();
            this.updateTables();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Aluno excluído");
        
    }//GEN-LAST:event_btnExcluirAlunoActionPerformed

    private void btnPesquisarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarAlunoActionPerformed
        // TODO add your handling code here:
        this.tableAluno.setModel(DbUtils.resultSetToTableModel(this.alunoDao.pesquisarTable(this.txtAlunoNome.getText())));
    }//GEN-LAST:event_btnPesquisarAlunoActionPerformed

    private void btnCadastrarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarLancamentoActionPerformed
        // TODO add your handling code here:
        try {
            Lancamento lancamento = new Lancamento(
                    Double.parseDouble(this.txtAltitudeMedia.getText()),
                    Double.parseDouble(this.txtVelocidadeMaxima.getText()),
                    Double.parseDouble(this.txtTaxaDescida.getText()),
                    this.txtTempoApogeuDescida.getText(),
                    Double.parseDouble(this.txtPicoAceleracao.getText()),
                    Double.parseDouble(this.txtAceleracaoMedia.getText()),
                    this.txtTempoPropulsao.getText(),
                    this.txtTempoEjecao.getText(),
                    Double.parseDouble(this.txtAltitudeEjecao.getText()),
                    this.txtDuracao.getText()
            );
            this.lancamentoDao.inserir(lancamento, this.selectLancamentoGrupo.getSelectedItem().toString());
            JOptionPane.showMessageDialog(null, "Lançamento cadastrado!");
            this.updateTables();
            this.updateComboBoxes();
        }catch(NumberFormatException | ParseException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_btnCadastrarLancamentoActionPerformed

    private void txtVelocidadeMaximaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVelocidadeMaximaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVelocidadeMaximaActionPerformed

    private void txtPicoAceleracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPicoAceleracaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPicoAceleracaoActionPerformed

    private void btnAlterarLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarLancamentoActionPerformed
        // TODO add your handling code here:
        Lancamento lancamento = new Lancamento(
                    Double.parseDouble(this.txtAltitudeMedia.getText()),
                    Double.parseDouble(this.txtVelocidadeMaxima.getText()),
                    Double.parseDouble(this.txtTaxaDescida.getText()),
                    this.txtTempoApogeuDescida.getText(),
                    Double.parseDouble(this.txtPicoAceleracao.getText()),
                    Double.parseDouble(this.txtAceleracaoMedia.getText()),
                    this.txtTempoPropulsao.getText(),
                    this.txtTempoEjecao.getText(),
                    Double.parseDouble(this.txtAltitudeEjecao.getText()),
                    this.txtDuracao.getText()
            );
         try {
            this.lancamentoDao.alterar(lancamento, this.lancamentoGrupoSelecionado);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        this.updateTables();
    }//GEN-LAST:event_btnAlterarLancamentoActionPerformed

    private void btnExcluirLancamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirLancamentoActionPerformed
        // TODO add your handling code here:
        try {
            this.lancamentoDao.deletar(this.lancamentoGrupoSelecionado);    
            this.updateComboBoxes();
            this.updateTables();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Aluno excluído");
    }//GEN-LAST:event_btnExcluirLancamentoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterarAluno;
    private javax.swing.JButton btnAlterarGrupo;
    private javax.swing.JButton btnAlterarLancamento;
    private javax.swing.JButton btnAlterarTurma;
    private javax.swing.JButton btnCadastrarAluno;
    private javax.swing.JButton btnCadastrarGrupo;
    private javax.swing.JButton btnCadastrarLancamento;
    private javax.swing.JButton btnCadastrarTurma;
    private javax.swing.JButton btnExcluirAluno;
    private javax.swing.JButton btnExcluirGrupo;
    private javax.swing.JButton btnExcluirLancamento;
    private javax.swing.JButton btnExcluirTurma;
    private javax.swing.JButton btnPesquisarAluno;
    private javax.swing.JButton btnPesquisarGrupo;
    private javax.swing.JButton btnPesquisarLancamento;
    private javax.swing.JButton btnPesquisarTurma;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JComboBox<String> selectGrupoCombo;
    private javax.swing.JComboBox<String> selectLancamentoGrupo;
    private javax.swing.JComboBox<String> selectTurma;
    private javax.swing.JTable tableAluno;
    private javax.swing.JTable tableGrupo;
    private javax.swing.JTable tableLancamento;
    private javax.swing.JTable tableTurma;
    private javax.swing.JTextField txtAceleracaoMedia;
    private javax.swing.JTextField txtAltitudeEjecao;
    private javax.swing.JTextField txtAltitudeMedia;
    private javax.swing.JTextField txtAlunoNome;
    private javax.swing.JTextField txtDuracao;
    private javax.swing.JTextField txtGrupoNome;
    private javax.swing.JTextField txtPicoAceleracao;
    private javax.swing.JTextField txtRMAluno;
    private javax.swing.JTextField txtTaxaDescida;
    private javax.swing.JTextField txtTempoApogeuDescida;
    private javax.swing.JTextField txtTempoEjecao;
    private javax.swing.JTextField txtTempoPropulsao;
    private javax.swing.JTextField txtTurma;
    private javax.swing.JTextField txtVelocidadeMaxima;
    // End of variables declaration//GEN-END:variables
}
