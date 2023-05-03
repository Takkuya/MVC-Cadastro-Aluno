package br.edu.unicid.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.edu.unicid.dao.AlunoDAO;
import br.edu.unicid.model.Aluno;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldRgm;
	private JComboBox comboBoxCurso_1;

	private AlunoDAO alunoDAO;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 321);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAluno = new JMenu("Aluno");
		mnAluno.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnAluno);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnAluno.add(mntmSalvar);
		
		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmAlterar);
		
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmConsultar);
		
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmExcluir);

		JSeparator separator_1 = new JSeparator();
		mnAluno.add(separator_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.SHIFT_DOWN_MASK));
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmNewMenuItem);

		JMenu mnNotasFaltas = new JMenu("Notas e Faltas");
		menuBar.add(mnNotasFaltas);
		
		JMenuItem mntmNotasFaltasSalvar = new JMenuItem("Salvar");
		mntmNotasFaltasSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasSalvar);
		
		JMenuItem mntmNotasFaltasAlterar = new JMenuItem("Alterar");
		mntmNotasFaltasAlterar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mntmNotasFaltasAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasAlterar);
		
		JMenuItem mntmNotasFaltasExcluir = new JMenuItem("Excluir");
		mntmNotasFaltasExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasExcluir);
		
		JMenuItem mntmNotasFaltasExcluir_1 = new JMenuItem("Excluir");
		mntmNotasFaltasExcluir_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasExcluir_1);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JButton btnAjudaSobre = new JButton("Sobre");
		btnAjudaSobre.setBorderPainted(false);
		mnAjuda.add(btnAjudaSobre);

		JSeparator separator = new JSeparator();
		menuBar.add(separator);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 469, 244);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tabbedPane);

		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelDadosPessoais.setBorder(null);
		tabbedPane.addTab("Dados Pessoais", null, panelDadosPessoais, null);
		panelDadosPessoais.setLayout(null);

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setBounds(10, 11, 39, 17);
		lblRgm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblRgm);

		formattedTextFieldRgm = new JFormattedTextField();
		formattedTextFieldRgm.setBounds(50, 11, 106, 20);
		formattedTextFieldRgm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldRgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(180, 11, 39, 17);
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblNome);

		JFormattedTextField formattedTextFieldNome = new JFormattedTextField();
		formattedTextFieldNome.setBounds(229, 9, 225, 20);
		formattedTextFieldNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldNome);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setBounds(10, 41, 123, 17);
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblDataNascimento);

		JFormattedTextField formattedTextFieldDataNascimento = new JFormattedTextField();
		formattedTextFieldDataNascimento.setBounds(143, 41, 106, 20);
		formattedTextFieldDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldDataNascimento);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(273, 41, 32, 17);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblCpf);

		JFormattedTextField formattedTextFieldCpf = new JFormattedTextField();
		formattedTextFieldCpf.setBounds(310, 40, 144, 20);
		formattedTextFieldCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldCpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 73, 39, 17);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblEmail);

		JFormattedTextField formattedTextFieldEmail = new JFormattedTextField();
		formattedTextFieldEmail.setBounds(50, 71, 404, 20);
		formattedTextFieldEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldEmail);

		JLabel lblEndereco = new JLabel("End.");
		lblEndereco.setBounds(10, 101, 39, 17);
		lblEndereco.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblEndereco);

		JFormattedTextField formattedTextFieldEnd = new JFormattedTextField();
		formattedTextFieldEnd.setBounds(50, 101, 404, 20);
		formattedTextFieldEnd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldEnd);

		JLabel lblMunicipio = new JLabel("Município");
		lblMunicipio.setBounds(10, 132, 65, 17);
		lblMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblMunicipio);

		JFormattedTextField formattedTextFieldEnd_1 = new JFormattedTextField();
		formattedTextFieldEnd_1.setBounds(76, 132, 80, 20);
		formattedTextFieldEnd_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldEnd_1);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(166, 132, 23, 17);
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblUf);

		JComboBox comboBoxUf = new JComboBox();
		comboBoxUf.setBounds(189, 132, 46, 22);
		comboBoxUf.setModel(new DefaultComboBoxModel(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		panelDadosPessoais.add(comboBoxUf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(258, 132, 47, 17);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblCelular);

		JFormattedTextField formattedTextFieldCelular = new JFormattedTextField();
		formattedTextFieldCelular.setBounds(310, 132, 144, 20);
		formattedTextFieldCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldCelular);

		JButton btnNewButton = new JButton("Mostrar alunos teste11");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					List<Aluno> lista = new ArrayList<Aluno>();
					alunoDAO = new AlunoDAO();
					lista = alunoDAO.listarTodos();

					for (Aluno aluno : lista) {
						System.out.println("Nome... " + aluno.getNome() + "\n");
					}

				} catch (Exception err) {
					System.out.println(err.getMessage());
				}
			}
		});
		btnNewButton.setBounds(273, 193, 209, 23);
		panelDadosPessoais.add(btnNewButton);
		
		JLabel lblErrorText = new JLabel("");
		lblErrorText.setForeground(new Color(255, 0, 0));
		lblErrorText.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblErrorText.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblErrorText.setBounds(10, 164, 444, 23);
		panelDadosPessoais.add(lblErrorText);

		JPanel panelCurso = new JPanel();
		panelCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurso.setBounds(10, 11, 46, 14);
		panelCurso.add(lblCurso);

		JComboBox comboBoxCurso = new JComboBox();
		comboBoxCurso.setModel(new DefaultComboBoxModel(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Administração", "Ciência da Computação", "Medicina" }));
		comboBoxCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCurso.setBounds(69, 9, 385, 22);
		panelCurso.add(comboBoxCurso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCampus.setBounds(10, 43, 58, 22);
		panelCurso.add(lblCampus);

		JComboBox comboCampus = new JComboBox();
		comboCampus.setModel(new DefaultComboBoxModel(new String[] { "Tatuapé", "Pinheiros" }));
		comboCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCampus.setBounds(69, 44, 385, 22);
		panelCurso.add(comboCampus);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodo.setBounds(10, 84, 58, 14);
		panelCurso.add(lblPeriodo);

		JRadioButton rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMatutino.setBounds(90, 82, 79, 23);
		panelCurso.add(rdbtnMatutino);

		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnVespertino.setBounds(188, 82, 95, 23);
		panelCurso.add(rdbtnVespertino);

		JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNoturno.setBounds(302, 82, 95, 23);
		panelCurso.add(rdbtnNoturno);

		JButton btnSave = new JButton("");
		btnSave.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\FloppyDiskBack.png"));
		btnSave.setBounds(34, 123, 60, 60);
		panelCurso.add(btnSave);

		JButton btnUpdate = new JButton("");
		btnUpdate.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\PencilSimpleLine.png"));
		btnUpdate.setBounds(114, 123, 60, 60);
		panelCurso.add(btnUpdate);

		JButton btnGet = new JButton("");
		btnGet.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\MagnifyingGlass.png"));
		btnGet.setBounds(201, 123, 60, 60);
		panelCurso.add(btnGet);

		JButton btnDelete = new JButton("");
		btnDelete.setIcon(
				new ImageIcon("C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\Trash.png"));
		btnDelete.setBounds(286, 123, 60, 60);
		panelCurso.add(btnDelete);

		JButton btnCleanAllFields = new JButton("");
		btnCleanAllFields
				.setIcon(new ImageIcon("C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\X.png"));
		btnCleanAllFields.setBounds(372, 123, 60, 60);
		panelCurso.add(btnCleanAllFields);

		JPanel panelNotasEFaltas = new JPanel();
		panelNotasEFaltas.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Notas e Faltas", null, panelNotasEFaltas, null);
		panelNotasEFaltas.setLayout(null);

		JLabel lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRgm_1.setBounds(10, 13, 39, 17);
		panelNotasEFaltas.add(lblRgm_1);

		JFormattedTextField formattedTextFieldRgm_1 = new JFormattedTextField();
		formattedTextFieldRgm_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldRgm_1.setBounds(51, 11, 149, 20);
		panelNotasEFaltas.add(formattedTextFieldRgm_1);

		JFormattedTextField formattedTextFieldNomeAluno = new JFormattedTextField();
		formattedTextFieldNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldNomeAluno.setBounds(210, 11, 244, 20);
		panelNotasEFaltas.add(formattedTextFieldNomeAluno);

		JFormattedTextField formattedTextFieldCursoAluno = new JFormattedTextField();
		formattedTextFieldCursoAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldCursoAluno.setBounds(10, 47, 444, 20);
		panelNotasEFaltas.add(formattedTextFieldCursoAluno);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDisciplina.setBounds(10, 82, 54, 17);
		panelNotasEFaltas.add(lblDisciplina);

		comboBoxCurso_1 = new JComboBox();
		comboBoxCurso_1.setModel(new DefaultComboBoxModel(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Administração", "Ciência da Computação", "Medicina" }));
		comboBoxCurso_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCurso_1.setBounds(79, 79, 375, 22);
		panelNotasEFaltas.add(comboBoxCurso_1);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSemestre.setBounds(10, 118, 62, 17);
		panelNotasEFaltas.add(lblSemestre);

		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setModel(new DefaultComboBoxModel(new String[] { "2020-1" }));
		comboBoxSemestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxSemestre.setBounds(79, 115, 80, 22);
		panelNotasEFaltas.add(comboBoxSemestre);

		JComboBox comboNota = new JComboBox();
		comboNota.setModel(new DefaultComboBoxModel(new String[] { "0,5" }));
		comboNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboNota.setBounds(227, 115, 62, 22);
		panelNotasEFaltas.add(comboNota);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNota.setBounds(183, 118, 39, 17);
		panelNotasEFaltas.add(lblNota);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFaltas.setBounds(311, 118, 39, 17);
		panelNotasEFaltas.add(lblFaltas);

		JFormattedTextField formattedTextFieldFaltas = new JFormattedTextField();
		formattedTextFieldFaltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextFieldFaltas.setBounds(354, 118, 100, 20);
		panelNotasEFaltas.add(formattedTextFieldFaltas);

		JButton btnSave_1 = new JButton("");
		btnSave_1.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\FloppyDiskBack.png"));
		btnSave_1.setBounds(34, 146, 60, 60);
		panelNotasEFaltas.add(btnSave_1);

		JButton btnUpdate_1 = new JButton("");
		btnUpdate_1.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\PencilSimpleLine.png"));
		btnUpdate_1.setBounds(114, 146, 60, 60);
		panelNotasEFaltas.add(btnUpdate_1);

		JButton btnGet_1 = new JButton("");
		btnGet_1.setIcon(new ImageIcon(
				"C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\MagnifyingGlass.png"));
		btnGet_1.setBounds(201, 146, 60, 60);
		panelNotasEFaltas.add(btnGet_1);

		JButton btnDelete_1 = new JButton("");
		btnDelete_1.setIcon(
				new ImageIcon("C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\Trash.png"));
		btnDelete_1.setBounds(286, 146, 60, 60);
		panelNotasEFaltas.add(btnDelete_1);

		JButton btnCleanAllFields_1 = new JButton("");
		btnCleanAllFields_1
				.setIcon(new ImageIcon("C:\\Users\\takuy\\eclipse-workspace\\MVC_Cadastro_Aluno\\src\\assets\\X.png"));
		btnCleanAllFields_1.setBounds(372, 146, 60, 60);
		panelNotasEFaltas.add(btnCleanAllFields_1);

		JPanel panelBoletim = new JPanel();
		panelBoletim.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
