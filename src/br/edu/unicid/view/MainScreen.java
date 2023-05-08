package br.edu.unicid.view;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import br.edu.unicid.dao.AlunoDAO;
import br.edu.unicid.dao.NotasFaltasDAO;
import br.edu.unicid.model.Aluno;
import br.edu.unicid.model.NotasFaltas;
import br.edu.unicid.utilities.ViewAlunoMethods;

public class MainScreen extends JFrame {
	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldRgm;
	private JComboBox<?> comboBoxCursoDisciplina;
	private Aluno aluno;
	private AlunoDAO alunoDAO;
	private NotasFaltas notasFaltas;
	private NotasFaltasDAO notasFaltasDAO;
	private String selectedPeriodo;
	private ViewAlunoMethods viewAlunoMethods = new ViewAlunoMethods();

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
	public MainScreen() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 308);

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

		String rgmMask = "########";

		formattedTextFieldRgm = new JFormattedTextField(new MaskFormatter(rgmMask));
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

		String dateMask = "####/##/##";

		JFormattedTextField formattedTextFieldDataNascimento = new JFormattedTextField(new MaskFormatter(dateMask));
		formattedTextFieldDataNascimento.setBounds(143, 41, 106, 20);
		formattedTextFieldDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldDataNascimento);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(273, 41, 32, 17);
		lblCpf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblCpf);

		String cpfMask = "###.###.###-##";

		JFormattedTextField formattedTextFieldCpf = new JFormattedTextField(new MaskFormatter(cpfMask));
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

		JFormattedTextField formattedTextFieldMunicipio = new JFormattedTextField();
		formattedTextFieldMunicipio.setBounds(76, 132, 80, 20);
		formattedTextFieldMunicipio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldMunicipio);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(166, 132, 23, 17);
		lblUf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblUf);

		JComboBox<String> comboBoxUf = new JComboBox<String>();
		comboBoxUf.setBounds(189, 132, 60, 22);
		comboBoxUf.setModel(new DefaultComboBoxModel<String>(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		panelDadosPessoais.add(comboBoxUf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(258, 132, 47, 17);
		lblCelular.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(lblCelular);

		String phoneMask = "(##)#####-####";

		JFormattedTextField formattedTextFieldCelular = new JFormattedTextField(new MaskFormatter(phoneMask));
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
		btnNewButton.setBounds(112, 163, 209, 23);
		panelDadosPessoais.add(btnNewButton);

		JPanel panelCurso = new JPanel();
		panelCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCurso.setBounds(10, 11, 46, 14);
		panelCurso.add(lblCurso);

		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setModel(new DefaultComboBoxModel<String>(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Administração", "Ciência da Computação", "Medicina" }));
		comboBoxCurso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxCurso.setBounds(69, 9, 385, 22);
		panelCurso.add(comboBoxCurso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCampus.setBounds(10, 43, 58, 22);
		panelCurso.add(lblCampus);

		JComboBox<String> comboCampus = new JComboBox<String>();
		comboCampus.setModel(new DefaultComboBoxModel<String>(new String[] { "Tatuapé", "Pinheiros" }));
		comboCampus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboCampus.setBounds(69, 44, 385, 22);
		panelCurso.add(comboCampus);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPeriodo.setBounds(10, 84, 58, 14);
		panelCurso.add(lblPeriodo);

		ButtonGroup radioBtnGroup = new ButtonGroup();

		JRadioButton rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Matutino";
			}
		});
		rdbtnMatutino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnMatutino.setBounds(90, 82, 79, 23);
		panelCurso.add(rdbtnMatutino);

		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Vespertino";
			}
		});
		rdbtnVespertino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnVespertino.setBounds(188, 82, 95, 23);
		panelCurso.add(rdbtnVespertino);

		JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Noturno";
			}
		});
		rdbtnNoturno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		rdbtnNoturno.setBounds(302, 82, 95, 23);
		panelCurso.add(rdbtnNoturno);

		ImageIcon saveIcon = new ImageIcon("assets/FloppyDiskBack.png");
		ImageIcon searchIcon = new ImageIcon("assets/MagnifyingGlass.png");
		ImageIcon editIcon = new ImageIcon("assets/PencilSimpleLine.png");
		ImageIcon trashIcon = new ImageIcon("assets/Trash.png");
		ImageIcon xIcon = new ImageIcon("assets/X.png");

		radioBtnGroup.add(rdbtnMatutino);
		radioBtnGroup.add(rdbtnVespertino);
		radioBtnGroup.add(rdbtnNoturno);

		JTextField[] textFields = { formattedTextFieldRgm, formattedTextFieldNome, formattedTextFieldDataNascimento,
				formattedTextFieldCpf, formattedTextFieldEmail, formattedTextFieldEnd, formattedTextFieldMunicipio,
				formattedTextFieldCelular };
		JComboBox[] comboBoxes = { comboBoxCurso, comboCampus, comboBoxUf };

		JButton btnSave = new JButton(saveIcon);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// validação do formulário
					viewAlunoMethods.formValidation(textFields, comboBoxes, selectedPeriodo);

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.saveAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar o aluno: " + err.getMessage());
				}
			}
		});

		btnSave.setToolTipText("Salvar");
		btnSave.setBounds(10, 121, 70, 70);
		panelCurso.add(btnSave);

		JButton btnUpdate = new JButton(editIcon);
		btnUpdate.setToolTipText("Alterar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// validação do formulário
					viewAlunoMethods.formValidation(textFields, comboBoxes, selectedPeriodo);

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.updateAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao alterar as informações do aluno: " + err.getMessage());
				}
			}
		});

		btnUpdate.setBounds(90, 121, 70, 70);
		panelCurso.add(btnUpdate);

		JButton btnGet = new JButton(searchIcon);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// caso o campo de RGM esteja vazio
					Aluno aluno = viewAlunoMethods.verifyRgm(formattedTextFieldRgm.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					String dataNascimentoFormatted = dateFormat.format(aluno.getDataDeNascimento());

					formattedTextFieldNome.setText(aluno.getNome());
					formattedTextFieldDataNascimento.setText(dataNascimentoFormatted);
					formattedTextFieldCpf.setText(aluno.getCpf());
					formattedTextFieldEmail.setText(aluno.getEmail());
					formattedTextFieldEnd.setText(aluno.getEndereco());
					formattedTextFieldMunicipio.setText(aluno.getMunicipio());
					comboBoxUf.setSelectedItem(aluno.getUf());
					formattedTextFieldCelular.setText(aluno.getCelular());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboCampus.setSelectedItem(aluno.getCampus());

					ButtonModel matutino = rdbtnMatutino.getModel();
					ButtonModel vespertino = rdbtnVespertino.getModel();
					ButtonModel noturno = rdbtnNoturno.getModel();

					if (aluno.getPeriodo().equals("Matutino")) {
						radioBtnGroup.setSelected(matutino, true);
						selectedPeriodo = "Matutino";
					}
					if (aluno.getPeriodo().equals("Vesperino")) {
						radioBtnGroup.setSelected(vespertino, true);
						selectedPeriodo = "Vesperino";
					}
					if (aluno.getPeriodo().equals("Noturno")) {
						radioBtnGroup.setSelected(noturno, true);
						selectedPeriodo = "Noturno";
					}

					JOptionPane.showMessageDialog(null, "Aluno encontrado!!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao consultar aluno: " + err.getMessage());
				}
			}
		});
		btnGet.setToolTipText("Procurar");
		btnGet.setBounds(170, 121, 70, 70);
		panelCurso.add(btnGet);

		JButton btnDelete = new JButton(trashIcon);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAlunoMethods.deleteAluno(formattedTextFieldRgm.getText());
			}
		});
		btnDelete.setToolTipText("Deletar");
		btnDelete.setBounds(249, 121, 70, 70);
		panelCurso.add(btnDelete);

		JButton btnCleanFields = new JButton("Limpar Campos");
		btnCleanFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgm.setText(null);
				formattedTextFieldNome.setText(null);
				formattedTextFieldEmail.setText(null);
				formattedTextFieldCelular.setText(null);
				formattedTextFieldMunicipio.setText(null);
				formattedTextFieldEnd.setText(null);
				formattedTextFieldCpf.setText(null);
				formattedTextFieldDataNascimento.setText(null);
				comboCampus.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxUf.setSelectedIndex(0);
				radioBtnGroup.clearSelection();
			}
		});
		btnCleanFields.setToolTipText("Limpar campos");
		btnCleanFields.setBounds(329, 121, 125, 70);
		panelCurso.add(btnCleanFields);

		JPanel panelNotasEFaltas = new JPanel();
		panelNotasEFaltas.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Notas e Faltas", null, panelNotasEFaltas, null);
		panelNotasEFaltas.setLayout(null);

		JLabel lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setBounds(10, 13, 39, 17);
		lblRgm_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(lblRgm_1);

		JFormattedTextField formattedTextFieldRgmNotas = new JFormattedTextField(new MaskFormatter(rgmMask));
		formattedTextFieldRgmNotas.setBounds(51, 11, 149, 20);
		formattedTextFieldRgmNotas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(formattedTextFieldRgmNotas);

		JFormattedTextField formattedTextFieldNomeAluno = new JFormattedTextField();
		formattedTextFieldNomeAluno.setBounds(210, 11, 244, 20);
		formattedTextFieldNomeAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(formattedTextFieldNomeAluno);

		JFormattedTextField formattedTextFieldCursoAluno = new JFormattedTextField();
		formattedTextFieldCursoAluno.setBounds(10, 39, 444, 20);
		formattedTextFieldCursoAluno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(formattedTextFieldCursoAluno);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 73, 54, 17);
		lblDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(lblDisciplina);

		comboBoxCursoDisciplina = new JComboBox();
		comboBoxCursoDisciplina.setBounds(79, 70, 375, 22);
		comboBoxCursoDisciplina.setModel(new DefaultComboBoxModel(new String[] {
				"Análise e Desenvolvimento de Sistemas", "Administração", "Ciência da Computação", "Medicina" }));
		comboBoxCursoDisciplina.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(comboBoxCursoDisciplina);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 109, 62, 17);
		lblSemestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(lblSemestre);

		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setBounds(79, 106, 80, 22);
		comboBoxSemestre.setModel(new DefaultComboBoxModel(new String[] { "2020-1", "2020-2", "2021-1", "2021-2",
				"2022-1", "2022-2", "2023-1", "2023-2", "2024-1", "2024-2" }));
		comboBoxSemestre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(comboBoxSemestre);

		JComboBox comboNota = new JComboBox();
		comboNota.setBounds(227, 106, 62, 22);
		comboNota.setModel(new DefaultComboBoxModel(new String[] { "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4",
				"4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10" }));
		comboNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(comboNota);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(183, 109, 39, 17);
		lblNota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(lblNota);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(311, 109, 39, 17);
		lblFaltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(lblFaltas);

		JFormattedTextField formattedTextFieldFaltas = new JFormattedTextField();
		formattedTextFieldFaltas.setBounds(354, 109, 100, 20);
		formattedTextFieldFaltas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelNotasEFaltas.add(formattedTextFieldFaltas);

		JButton btnNotasSave = new JButton(saveIcon);
		btnNotasSave.setBounds(10, 140, 70, 70);
		btnNotasSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					notasFaltasDAO = new NotasFaltasDAO();
					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(formattedTextFieldFaltas.getText());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.salvar(notasFaltas);

					JOptionPane.showMessageDialog(null, "Informações inseridas com sucesso!!");
				} catch (Exception err) {
//					System.out.println("disciplina..." + comboBoxCursoDisciplina.getSelectedItem().toString());
//					System.out.println("semestre..." + comboBoxSemestre.getSelectedItem().toString());
//					System.out.println("nota..." + comboNota.getSelectedItem().toString());
//					System.out.println("falta..." + notasFaltas.getFalta());
//					System.out.println("rgm..." + formattedTextFieldRgmNotas.getText());
					System.err.println("Ocorreu um erro ao salvar a nota: " + err.getMessage());
				}
			}
		});
		btnNotasSave.setToolTipText("Salvar");
		panelNotasEFaltas.add(btnNotasSave);

		JButton btnNotasUpdate = new JButton(editIcon);
		btnNotasUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					notasFaltasDAO = new NotasFaltasDAO();
					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(formattedTextFieldFaltas.getText());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.alterar(notasFaltas);
					
					JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!!");
				} catch (Exception err) {
					System.err.println("Erro ao alterar nota:" + err.getMessage());
				}
			}
		});
		btnNotasUpdate.setToolTipText("Alterar");
		btnNotasUpdate.setBounds(90, 140, 70, 70);
		panelNotasEFaltas.add(btnNotasUpdate);

		JButton btnNotasGet = new JButton(searchIcon);
		btnNotasGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// caso o campo de RGM esteja vazio
					Aluno aluno = viewAlunoMethods.verifyRgm(formattedTextFieldRgmNotas.getText());

					formattedTextFieldNomeAluno.setText(aluno.getNome());
					formattedTextFieldCursoAluno.setText(aluno.getCurso());

					JOptionPane.showMessageDialog(null, "Aluno encontrado!!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao consultar aluno: " + err.getMessage());
				}
			}
		});
		btnNotasGet.setToolTipText("Listar");
		btnNotasGet.setBounds(170, 140, 70, 70);
		panelNotasEFaltas.add(btnNotasGet);

		JButton btnNotasDelete = new JButton(trashIcon);
		btnNotasDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					notasFaltasDAO = new NotasFaltasDAO();

					String rgm = formattedTextFieldRgmNotas.getText();
					String disciplina = comboBoxCursoDisciplina.getSelectedItem().toString();
					String semestre = comboBoxSemestre.getSelectedItem().toString();

					notasFaltasDAO.excluir(rgm, disciplina, semestre);

					JOptionPane.showMessageDialog(null, "Nota excluída com sucesso!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao deletar a nota: " + err.getMessage());
				}
			}
		});
		btnNotasDelete.setToolTipText("Deletar");
		btnNotasDelete.setBounds(249, 140, 70, 70);
		panelNotasEFaltas.add(btnNotasDelete);

		JButton btnNotasCleanFields = new JButton(xIcon);
		btnNotasCleanFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgmNotas.setText(null);
				formattedTextFieldNomeAluno.setText(null);
				formattedTextFieldCursoAluno.setText(null);

				comboBoxCursoDisciplina.setSelectedIndex(0);
				comboBoxSemestre.setSelectedIndex(0);
				comboNota.setSelectedIndex(0);
				formattedTextFieldFaltas.setText(null);
			}
		});
		btnNotasCleanFields.setText("Limpar Campos");
		btnNotasCleanFields.setToolTipText("Limpar Campos");
		btnNotasCleanFields.setBounds(329, 140, 125, 70);
		panelNotasEFaltas.add(btnNotasCleanFields);

		JPanel panelBoletim = new JPanel();
		panelBoletim.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Boletim", null, panelBoletim, null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAluno = new JMenu("Aluno");
		mnAluno.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnAluno);

		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// validação do formulário
					viewAlunoMethods.formValidation(textFields, comboBoxes, selectedPeriodo);

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.saveAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar o aluno: " + err.getMessage());
				}
			}
		});
		mntmSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnAluno.add(mntmSalvar);

		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// validação do formulário
					viewAlunoMethods.formValidation(textFields, comboBoxes, selectedPeriodo);

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.updateAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao alterar as informações do aluno: " + err.getMessage());
				}
			}
		});
		mntmAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmAlterar);

		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// caso o campo de RGM esteja vazio
					Aluno aluno = viewAlunoMethods.verifyRgm(formattedTextFieldRgm.getText());

					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					String dataNascimentoFormatted = dateFormat.format(aluno.getDataDeNascimento());

					formattedTextFieldNome.setText(aluno.getNome());
					formattedTextFieldDataNascimento.setText(dataNascimentoFormatted);
					formattedTextFieldCpf.setText(aluno.getCpf());
					formattedTextFieldEmail.setText(aluno.getEmail());
					formattedTextFieldEnd.setText(aluno.getEndereco());
					formattedTextFieldMunicipio.setText(aluno.getMunicipio());
					comboBoxUf.setSelectedItem(aluno.getUf());
					formattedTextFieldCelular.setText(aluno.getCelular());
					comboBoxCurso.setSelectedItem(aluno.getCurso());
					comboCampus.setSelectedItem(aluno.getCampus());

					ButtonModel matutino = rdbtnMatutino.getModel();
					ButtonModel vespertino = rdbtnVespertino.getModel();
					ButtonModel noturno = rdbtnNoturno.getModel();

					if (aluno.getPeriodo().equals("Matutino")) {
						radioBtnGroup.setSelected(matutino, true);
						selectedPeriodo = "Matutino";
					}
					if (aluno.getPeriodo().equals("Vesperino")) {
						radioBtnGroup.setSelected(vespertino, true);
						selectedPeriodo = "Vesperino";
					}
					if (aluno.getPeriodo().equals("Noturno")) {
						radioBtnGroup.setSelected(noturno, true);
						selectedPeriodo = "Noturno";
					}

					JOptionPane.showMessageDialog(null, "Aluno encontrado!!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao consultar aluno: " + err.getMessage());
				}
			}
		});
		mntmConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmConsultar);

		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAlunoMethods.deleteAluno(formattedTextFieldRgm.getText());
			}
		});
		mntmExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmExcluir);

		JSeparator separator_1 = new JSeparator();
		mnAluno.add(separator_1);

		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgm.setText(null);
				formattedTextFieldNome.setText(null);
				formattedTextFieldEmail.setText(null);
				formattedTextFieldCelular.setText(null);
				formattedTextFieldMunicipio.setText(null);
				formattedTextFieldEnd.setText(null);
				formattedTextFieldCpf.setText(null);
				formattedTextFieldDataNascimento.setText(null);
				comboCampus.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxUf.setSelectedIndex(0);
				radioBtnGroup.clearSelection();
			}
		});
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

		JMenuItem mntmNotasFaltasConsultar = new JMenuItem("Consultar");
		mntmNotasFaltasConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasConsultar);

		JMenu mnAjuda = new JMenu("Ajuda");
		menuBar.add(mnAjuda);

		JMenuItem mntmAjudaSobre = new JMenuItem("Sobre");
		mntmAjudaSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Projeto MVC - Cadastro de Aluno \n\n Integrantes do grupo: \n Gabriel Araujo de Jesus \n Gabriel Takuya Yamamoto");
			}
		});
		mntmAjudaSobre.setHorizontalAlignment(SwingConstants.CENTER);
		mnAjuda.add(mntmAjudaSobre);

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
