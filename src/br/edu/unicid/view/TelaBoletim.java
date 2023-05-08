package br.edu.unicid.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaBoletim extends JFrame {

	private JPanel contentPane;
	 private JLabel labelRGM;
	    private JLabel labelNome;
	    private JLabel labelCurso;
	    private JTextField fieldRGM;
	    private JTextField fieldNome;
	    private JTextField fieldCurso;
	    private JTable tableDisciplinas;
	    private JButton buttonSalvar;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaBoletim frame = new TelaBoletim();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaBoletim() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		setTitle("Boletim");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Painel para o topo da tela com os dados do aluno
        JPanel panelAluno = new JPanel(new GridLayout(3, 2, 5, 5));
        labelRGM = new JLabel("RGM:");
        fieldRGM = new JTextField();
        labelNome = new JLabel("Nome:");
        fieldNome = new JTextField();
        labelCurso = new JLabel("Curso:");
        fieldCurso = new JTextField();
        panelAluno.add(labelRGM);
        panelAluno.add(fieldRGM);
        panelAluno.add(labelNome);
        panelAluno.add(fieldNome);
        panelAluno.add(labelCurso);
        panelAluno.add(fieldCurso);
        add(panelAluno, BorderLayout.NORTH);
        
        // Painel para a tabela de disciplinas com notas e faltas
        JPanel panelDisciplinas = new JPanel(new BorderLayout());
        String[] colunas = {"Disciplina", "Nota", "Faltas"};
        Object[][] dados = {
            {"Matemática", "8.5", "2"},
            {"Português", "7.0", "3"},
            {"Inglês", "9.0", "1"}
        };
		DefaultTableModel model = new DefaultTableModel(dados, colunas);
        tableDisciplinas = new JTable(model);
        panelDisciplinas.add(new JScrollPane(tableDisciplinas), BorderLayout.CENTER);
        add(panelDisciplinas, BorderLayout.CENTER);
        
        // Painel para o botão de salvar
        JPanel panelSalvar = new JPanel(new FlowLayout());
        buttonSalvar = new JButton("Salvar");
        panelSalvar.add(buttonSalvar);
        add(panelSalvar, BorderLayout.SOUTH);
	}

}
