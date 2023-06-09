package br.edu.unicid.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import java.awt.SystemColor;
import javax.swing.JSpinner;

public class MainScreen extends JFrame {
	private JPanel contentPane;
	private JFormattedTextField formattedTextFieldRgm;
	private static JComboBox<?> comboBoxCursoDisciplina;
	private AlunoDAO alunoDAO;
	private NotasFaltas notasFaltas;
	private NotasFaltasDAO notasFaltasDAO;
	private String selectedPeriodo;
	private ViewAlunoMethods viewAlunoMethods = new ViewAlunoMethods();
	private String errorMessage = "";
	private boolean hasEmptyField = false;
	private Aluno aluno;

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
		setBounds(100, 100, 700, 500);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 5, 669, 400);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		contentPane.add(tabbedPane);

		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais.setFont(new Font("Roboto", Font.BOLD, 14));
		panelDadosPessoais.setBorder(null);
		tabbedPane.addTab("Dados Pessoais", null, panelDadosPessoais, null);
		panelDadosPessoais.setLayout(null);

		JLabel lblRgm = new JLabel("RGM");
		lblRgm.setBounds(10, 29, 39, 17);
		lblRgm.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblRgm);

		String rgmMask = "########";

		formattedTextFieldRgm = new JFormattedTextField(new MaskFormatter(rgmMask));
		formattedTextFieldRgm.setBounds(66, 25, 233, 25);
		formattedTextFieldRgm.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldRgm);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(310, 29, 47, 17);
		lblNome.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblNome);

		JFormattedTextField formattedTextFieldNome = new JFormattedTextField();
		formattedTextFieldNome.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldNome.setBounds(357, 26, 298, 25);
		formattedTextFieldNome.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldNome);

		JLabel lblDataNascimento = new JLabel("Data de Nascimento");
		lblDataNascimento.setBounds(10, 70, 147, 17);
		lblDataNascimento.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblDataNascimento);

		String dateMask = "####/##/##";

		JFormattedTextField formattedTextFieldDataNascimento = new JFormattedTextField(new MaskFormatter(dateMask));
		formattedTextFieldDataNascimento.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldDataNascimento.setBounds(167, 67, 133, 25);
		formattedTextFieldDataNascimento.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldDataNascimento);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(310, 70, 39, 17);
		lblCpf.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblCpf);

		String cpfMask = "###.###.###-##";

		JFormattedTextField formattedTextFieldCpf = new JFormattedTextField(new MaskFormatter(cpfMask));
		formattedTextFieldCpf.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldCpf.setBounds(357, 67, 182, 25);
		formattedTextFieldCpf.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldCpf);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(9, 109, 47, 17);
		lblEmail.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblEmail);

		JFormattedTextField formattedTextFieldEmail = new JFormattedTextField();
		formattedTextFieldEmail.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldEmail.setBounds(66, 105, 588, 25);
		formattedTextFieldEmail.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldEmail);

		JLabel lblEndereco = new JLabel("End.");
		lblEndereco.setBounds(10, 154, 39, 17);
		lblEndereco.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblEndereco);

		JFormattedTextField formattedTextFieldEnd = new JFormattedTextField();
		formattedTextFieldEnd.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldEnd.setBounds(66, 151, 589, 25);
		formattedTextFieldEnd.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldEnd);

		JLabel lblMunicipio = new JLabel("Município");
		lblMunicipio.setBounds(10, 192, 70, 17);
		lblMunicipio.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblMunicipio);

		JFormattedTextField formattedTextFieldMunicipio = new JFormattedTextField();
		formattedTextFieldMunicipio.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldMunicipio.setBounds(90, 189, 80, 25);
		formattedTextFieldMunicipio.setFont(new Font("Roboto", Font.PLAIN, 14));
		panelDadosPessoais.add(formattedTextFieldMunicipio);

		JLabel lblUf = new JLabel("UF");
		lblUf.setBounds(195, 192, 23, 17);
		lblUf.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblUf);

		JComboBox<String> comboBoxUf = new JComboBox<String>();
		comboBoxUf.setFont(new Font("Roboto", Font.PLAIN, 14));
		comboBoxUf.setBounds(226, 189, 60, 25);
		comboBoxUf.setModel(new DefaultComboBoxModel<String>(
				new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MS", "MT", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		panelDadosPessoais.add(comboBoxUf);

		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(308, 192, 60, 17);
		lblCelular.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelDadosPessoais.add(lblCelular);

		String phoneMask = "(##)#####-####";

		JFormattedTextField formattedTextFieldCelular = new JFormattedTextField(new MaskFormatter(phoneMask));
		formattedTextFieldCelular.setMinimumSize(new Dimension(7, 25));
		formattedTextFieldCelular.setBounds(372, 189, 168, 25);
		formattedTextFieldCelular.setFont(new Font("Roboto", Font.PLAIN, 14));

		panelDadosPessoais.add(formattedTextFieldCelular);

		JPanel panelCurso = new JPanel();
		panelCurso.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Curso", null, panelCurso, null);
		panelCurso.setLayout(null);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCurso.setBounds(10, 24, 46, 14);
		panelCurso.add(lblCurso);

		JComboBox<String> comboBoxCurso = new JComboBox<String>();
		comboBoxCurso.setModel(new DefaultComboBoxModel<String>(new String[] { "Análise e Desenvolvimento de Sistemas",
				"Administração", "Ciência da Computação", "Medicina" }));
		comboBoxCurso.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboBoxCurso.setBounds(78, 22, 573, 25);
		panelCurso.add(comboBoxCurso);

		JLabel lblCampus = new JLabel("Campus");
		lblCampus.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblCampus.setBounds(10, 61, 61, 22);
		panelCurso.add(lblCampus);

		JComboBox<String> comboCampus = new JComboBox<String>();
		comboCampus.setModel(new DefaultComboBoxModel<String>(new String[] { "Tatuapé", "Pinheiros" }));
		comboCampus.setFont(new Font("Roboto", Font.PLAIN, 16));
		comboCampus.setBounds(78, 60, 573, 25);
		panelCurso.add(comboCampus);

		JLabel lblPeriodo = new JLabel("Período");
		lblPeriodo.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblPeriodo.setBounds(10, 102, 58, 14);
		panelCurso.add(lblPeriodo);

		ButtonGroup radioBtnGroup = new ButtonGroup();

		JRadioButton rdbtnMatutino = new JRadioButton("Matutino");
		rdbtnMatutino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Matutino";
			}
		});
		rdbtnMatutino.setFont(new Font("Roboto", Font.PLAIN, 16));
		rdbtnMatutino.setBounds(78, 98, 95, 23);
		panelCurso.add(rdbtnMatutino);

		JRadioButton rdbtnVespertino = new JRadioButton("Vespertino");
		rdbtnVespertino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Vespertino";
			}
		});
		rdbtnVespertino.setFont(new Font("Roboto", Font.PLAIN, 16));
		rdbtnVespertino.setBounds(192, 98, 107, 23);
		panelCurso.add(rdbtnVespertino);

		JRadioButton rdbtnNoturno = new JRadioButton("Noturno");
		rdbtnNoturno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedPeriodo = "Noturno";
			}
		});
		rdbtnNoturno.setFont(new Font("Roboto", Font.PLAIN, 16));
		rdbtnNoturno.setBounds(324, 98, 95, 23);
		panelCurso.add(rdbtnNoturno);

		radioBtnGroup.add(rdbtnMatutino);
		radioBtnGroup.add(rdbtnVespertino);
		radioBtnGroup.add(rdbtnNoturno);

		JTextField[] textFields = { formattedTextFieldRgm, formattedTextFieldNome, formattedTextFieldDataNascimento,
				formattedTextFieldCpf, formattedTextFieldEmail, formattedTextFieldEnd, formattedTextFieldMunicipio,
				formattedTextFieldCelular };
		JComboBox[] comboBoxes = { comboBoxCurso, comboCampus, comboBoxUf };

		Map<JTextField, String> camposDoCurso = new HashMap<>();
		camposDoCurso.put(formattedTextFieldNome, "Nome");
		camposDoCurso.put(formattedTextFieldEmail, "Email");
		camposDoCurso.put(formattedTextFieldMunicipio, "Município");
		camposDoCurso.put(formattedTextFieldEnd, "Endereço");

		// salvar aluno
		JButton btnSave = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/FloppyDiskBack.png")));
		btnSave.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hasEmptyField = false;

					// validação nos campos de texto
					for (JTextField textField : textFields) {
						if (textField.getText().trim().isEmpty()) {
							hasEmptyField = true;
							errorMessage = "Preencha todos os campos (não esqueça da aba de cursos)";
							break;
						}
					}

					// validação nas combo box
					if (!hasEmptyField) {
						for (JComboBox comboBox : comboBoxes) {
							if (comboBox.getSelectedItem() == null) {
								hasEmptyField = true;
								errorMessage = "Selecione uma opção em todos os campos do curso";
								break;
							}
						}
					}

					// validação caso o campo de período esteja vazio
					if (!hasEmptyField && selectedPeriodo == null) {
						hasEmptyField = true;
						errorMessage = "Preencha o período em que você estuda";
					}

					// validação se há algum campo vazio
					if (hasEmptyField) {
						JOptionPane.showMessageDialog(null, errorMessage);
						return;
					}

					// validação tamanho dos campos
					for (Map.Entry<JTextField, String> campo : camposDoCurso.entrySet()) {
						if (campo.getKey().getText().trim().length() > 60) {
							JOptionPane.showMessageDialog(null,
									"O campo " + campo.getValue() + " deve conter no máximo 50 caracteres! ");
							return;
						}
					}

					// validação email é válido
					if (!formattedTextFieldEmail.getText().contains("@")
							|| !formattedTextFieldEmail.getText().contains(".")) {
						JOptionPane.showMessageDialog(null, "Insira um email válido! email@exemplo.com");
						return;
					}

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.saveAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

					formattedTextFieldRgm.setValue(null);
					formattedTextFieldNome.setText(null);
					formattedTextFieldEmail.setText(null);
					formattedTextFieldCelular.setValue(null);
					formattedTextFieldMunicipio.setText(null);
					formattedTextFieldEnd.setText(null);
					formattedTextFieldCpf.setValue(null);
					formattedTextFieldDataNascimento.setValue(null);
					comboCampus.setSelectedIndex(0);
					comboBoxCurso.setSelectedIndex(0);
					comboBoxUf.setSelectedIndex(0);
					radioBtnGroup.clearSelection();
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar o aluno: " + err.getMessage());
				}
			}
		});

		btnSave.setToolTipText("Salvar");
		btnSave.setBounds(25, 168, 100, 100);
		panelCurso.add(btnSave);

		// alterar aluno
		JButton btnUpdate = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/PencilSimpleLine.png")));
		btnUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnUpdate.setToolTipText("Alterar");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					hasEmptyField = false;

					// validação nos campos de texto
					for (JTextField textField : textFields) {
						if (textField.getText().trim().isEmpty()) {
							hasEmptyField = true;
							errorMessage = "Preencha todos os campos (não esqueça da aba de cursos)";
							break;
						}
					}

					// validação nas combo box
					if (!hasEmptyField) {
						for (JComboBox comboBox : comboBoxes) {
							if (comboBox.getSelectedItem() == null) {
								hasEmptyField = true;
								errorMessage = "Selecione uma opção em todos os campos do curso";
								break;
							}
						}
					}

					// validação caso o campo de período esteja vazio
					if (!hasEmptyField && selectedPeriodo == null) {
						hasEmptyField = true;
						errorMessage = "Preencha o período em que você estuda";
					}

					// validação se há algum campo vazio
					if (hasEmptyField) {
						JOptionPane.showMessageDialog(null, errorMessage);
						return;
					}

					// validação tamanho dos campos
					for (Map.Entry<JTextField, String> campo : camposDoCurso.entrySet()) {
						if (campo.getKey().getText().trim().length() > 60) {
							JOptionPane.showMessageDialog(null,
									"O campo " + campo.getValue() + " deve conter no máximo 50 caracteres! ");
							return;
						}
					}

					// validação email é válido
					if (!formattedTextFieldEmail.getText().contains("@")
							|| !formattedTextFieldEmail.getText().contains(".")) {
						JOptionPane.showMessageDialog(null, "Insira um email válido! email@exemplo.com");
						return;
					}

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.updateAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

					formattedTextFieldRgm.setValue(null);
					formattedTextFieldNome.setText(null);
					formattedTextFieldEmail.setText(null);
					formattedTextFieldCelular.setValue(null);
					formattedTextFieldMunicipio.setText(null);
					formattedTextFieldEnd.setText(null);
					formattedTextFieldCpf.setValue(null);
					formattedTextFieldDataNascimento.setValue(null);
					comboCampus.setSelectedIndex(0);
					comboBoxCurso.setSelectedIndex(0);
					comboBoxUf.setSelectedIndex(0);
					radioBtnGroup.clearSelection();
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao alterar as informações do aluno: " + err.getMessage());
				}
			}
		});

		btnUpdate.setBounds(135, 168, 100, 100);
		panelCurso.add(btnUpdate);

		// consultar aluno
		JButton btnGet = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/MagnifyingGlass.png")));
		btnGet.setFont(new Font("Roboto", Font.PLAIN, 16));
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
		btnGet.setBounds(245, 168, 100, 100);
		panelCurso.add(btnGet);

		// deletar aluno
		JButton btnDelete = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/Trash.png")));
		btnDelete.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAlunoMethods.deleteAluno(formattedTextFieldRgm.getText());

				formattedTextFieldRgm.setValue(null);
				formattedTextFieldNome.setText(null);
				formattedTextFieldEmail.setText(null);
				formattedTextFieldCelular.setValue(null);
				formattedTextFieldMunicipio.setText(null);
				formattedTextFieldEnd.setText(null);
				formattedTextFieldCpf.setValue(null);
				formattedTextFieldDataNascimento.setValue(null);
				comboCampus.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxUf.setSelectedIndex(0);
				radioBtnGroup.clearSelection();
			}
		});
		btnDelete.setToolTipText("Deletar");
		btnDelete.setBounds(355, 168, 100, 100);
		panelCurso.add(btnDelete);

		// limpar os campos do aluno
		JButton btnCleanFields = new JButton("Limpar Campos");
		btnCleanFields.setFont(new Font("Roboto", Font.BOLD, 16));
		btnCleanFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgm.setValue(null);
				formattedTextFieldNome.setText(null);
				formattedTextFieldEmail.setText(null);
				formattedTextFieldCelular.setValue(null);
				formattedTextFieldMunicipio.setText(null);
				formattedTextFieldEnd.setText(null);
				formattedTextFieldCpf.setValue(null);
				formattedTextFieldDataNascimento.setValue(null);
				comboCampus.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxUf.setSelectedIndex(0);
				radioBtnGroup.clearSelection();
			}
		});
		btnCleanFields.setToolTipText("Limpar campos");
		btnCleanFields.setBounds(465, 168, 174, 100);
		panelCurso.add(btnCleanFields);

		JPanel panelNotasEFaltas = new JPanel();
		panelNotasEFaltas.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Notas e Faltas", null, panelNotasEFaltas, null);
		panelNotasEFaltas.setLayout(null);

		JLabel lblRgm_1 = new JLabel("RGM");
		lblRgm_1.setBounds(10, 30, 39, 17);
		lblRgm_1.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(lblRgm_1);

		JFormattedTextField formattedTextFieldRgmNotas = new JFormattedTextField(new MaskFormatter(rgmMask));
		formattedTextFieldRgmNotas.setBounds(59, 26, 260, 25);
		formattedTextFieldRgmNotas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(formattedTextFieldRgmNotas);

		JFormattedTextField formattedTextFieldNomeAluno = new JFormattedTextField();
		formattedTextFieldNomeAluno.setEditable(false);
		formattedTextFieldNomeAluno.setDisabledTextColor(SystemColor.desktop);
		formattedTextFieldNomeAluno.setEnabled(false);
		formattedTextFieldNomeAluno.setBounds(339, 26, 315, 25);
		formattedTextFieldNomeAluno.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(formattedTextFieldNomeAluno);

		JFormattedTextField formattedTextFieldCursoAluno = new JFormattedTextField();
		formattedTextFieldCursoAluno.setEditable(false);
		formattedTextFieldCursoAluno.setEnabled(false);
		formattedTextFieldCursoAluno.setDisabledTextColor(SystemColor.desktop);
		formattedTextFieldCursoAluno.setBounds(59, 62, 595, 25);
		formattedTextFieldCursoAluno.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(formattedTextFieldCursoAluno);

		JLabel lblDisciplina = new JLabel("Disciplina");
		lblDisciplina.setBounds(10, 101, 70, 17);
		lblDisciplina.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(lblDisciplina);

		comboBoxCursoDisciplina = new JComboBox();
		comboBoxCursoDisciplina.setBounds(90, 98, 564, 25);
		comboBoxCursoDisciplina.setModel(new DefaultComboBoxModel(new String[] { "Programação Orientada a objetos",
				"Introdução ao Java", "Programação Web", "Introdução ao ReactJS", "O que são frameworks",
				"Tratamento de Dados utilizando pandas e numpy" }));
		comboBoxCursoDisciplina.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(comboBoxCursoDisciplina);

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setBounds(10, 141, 70, 17);
		lblSemestre.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(lblSemestre);

		JComboBox comboBoxSemestre = new JComboBox();
		comboBoxSemestre.setBounds(90, 137, 100, 25);
		comboBoxSemestre.setModel(new DefaultComboBoxModel(new String[] { "2020-1", "2020-2", "2021-1", "2021-2",
				"2022-1", "2022-2", "2023-1", "2023-2", "2024-1", "2024-2" }));
		comboBoxSemestre.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(comboBoxSemestre);

		JComboBox comboNota = new JComboBox();
		comboNota.setBounds(260, 137, 78, 25);
		comboNota.setModel(new DefaultComboBoxModel(new String[] { "0", "0.5", "1", "1.5", "2", "2.5", "3", "3.5", "4",
				"4.5", "5", "5.5", "6", "6.5", "7", "7.5", "8", "8.5", "9", "9.5", "10" }));
		comboNota.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(comboNota);

		JLabel lblNota = new JLabel("Nota");
		lblNota.setBounds(211, 141, 39, 17);
		lblNota.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(lblNota);

		JLabel lblFaltas = new JLabel("Faltas");
		lblFaltas.setBounds(360, 141, 44, 17);
		lblFaltas.setFont(new Font("Roboto", Font.PLAIN, 16));
		panelNotasEFaltas.add(lblFaltas);

		JSpinner spinnerFaltas = new JSpinner();
		spinnerFaltas.setFont(new Font("Roboto", Font.PLAIN, 16));
		spinnerFaltas.setBounds(414, 137, 70, 25);
		panelNotasEFaltas.add(spinnerFaltas);

		// salvar notas
		JButton btnNotasSave = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/FloppyDiskBack.png")));
		btnNotasSave.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnNotasSave.setBounds(26, 198, 100, 100);
		btnNotasSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					// caso o campo de RGM esteja vazio
					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// Verifica se o valor de faltas é maior que 30 ou menor que 0
					if (Integer.parseInt(spinnerFaltas.getValue().toString()) > 30
							|| Integer.parseInt(spinnerFaltas.getValue().toString()) < 0) {
						JOptionPane.showMessageDialog(null,
								"O número de faltas não pode ser maior do que 30 e nem menor que 0.");
						return;
					}

					System.out.println("print" + spinnerFaltas.getValue());

					notasFaltasDAO = new NotasFaltasDAO();

					// verifica se a discplina e o semestre já existem para este aluno
					if (notasFaltasDAO.verificarDisciplinaSemestreExistente(formattedTextFieldRgmNotas.getText(),
							comboBoxCursoDisciplina.getSelectedItem().toString(),
							comboBoxSemestre.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "A disciplina e o semestre já existem para este aluno!");
						return;
					}

					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(spinnerFaltas.getValue().toString());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.salvar(notasFaltas);

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);

					JOptionPane.showMessageDialog(null, "Informações inseridas com sucesso!!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar a nota: " + err.getMessage());
				}
			}
		});
		btnNotasSave.setToolTipText("Salvar");
		panelNotasEFaltas.add(btnNotasSave);

		// alterar notas
		JButton btnNotasUpdate = new JButton(
				new ImageIcon(MainScreen.class.getResource("/assets/PencilSimpleLine.png")));
		btnNotasUpdate.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnNotasUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// Verifica se o valor de faltas é maior que 30 ou menor que 0
					if (Integer.parseInt(spinnerFaltas.getValue().toString()) > 30
							|| Integer.parseInt(spinnerFaltas.getValue().toString()) < 0) {
						JOptionPane.showMessageDialog(null,
								"O número de faltas não pode ser maior do que 30 e nem menor que 0.");
						return;
					}

					notasFaltasDAO = new NotasFaltasDAO();

					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(spinnerFaltas.getValue().toString());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.alterar(notasFaltas);

					JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!!");

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);
				} catch (Exception err) {
					System.err.println("Erro ao alterar nota:" + err.getMessage());
				}
			}
		});
		btnNotasUpdate.setToolTipText("Alterar");
		btnNotasUpdate.setBounds(136, 198, 100, 100);
		panelNotasEFaltas.add(btnNotasUpdate);

		// consultar notas
		JButton btnNotasGet = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/MagnifyingGlass.png")));
		btnNotasGet.setFont(new Font("Roboto", Font.PLAIN, 16));
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
		btnNotasGet.setBounds(250, 198, 100, 100);
		panelNotasEFaltas.add(btnNotasGet);

		// deletar notas
		JButton btnNotasDelete = new JButton(new ImageIcon(MainScreen.class.getResource("/assets/Trash.png")));
		btnNotasDelete.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnNotasDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					notasFaltasDAO = new NotasFaltasDAO();

					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// verifica se a disciplina e o semestre não existem para este aluno
					if (!notasFaltasDAO.verificarDisciplinaSemestreExistente(formattedTextFieldRgmNotas.getText(),
							comboBoxCursoDisciplina.getSelectedItem().toString(),
							comboBoxSemestre.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "A disciplina e o semestre não existem para este aluno!");
						return;
					}

					String rgm = formattedTextFieldRgmNotas.getText();
					String disciplina = comboBoxCursoDisciplina.getSelectedItem().toString();
					String semestre = comboBoxSemestre.getSelectedItem().toString();

					notasFaltasDAO.excluir(rgm, disciplina, semestre);

					JOptionPane.showMessageDialog(null, "Nota excluída com sucesso!");

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao deletar a nota: " + err.getMessage());
				}
			}
		});
		btnNotasDelete.setToolTipText("Deletar");
		btnNotasDelete.setBounds(360, 198, 100, 100);
		panelNotasEFaltas.add(btnNotasDelete);

		// limpar campos de notas
		JButton btnNotasCleanFields = new JButton();
		btnNotasCleanFields.setFont(new Font("Roboto", Font.BOLD, 16));
		btnNotasCleanFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgmNotas.setValue(null);
				formattedTextFieldNomeAluno.setText(null);
				formattedTextFieldCursoAluno.setText(null);
				spinnerFaltas.setValue(0);

				comboBoxCursoDisciplina.setSelectedIndex(0);
				comboBoxSemestre.setSelectedIndex(0);
				comboNota.setSelectedIndex(0);
			}
		});
		btnNotasCleanFields.setText("Limpar Campos");
		btnNotasCleanFields.setToolTipText("Limpar Campos");
		btnNotasCleanFields.setBounds(470, 198, 174, 100);
		panelNotasEFaltas.add(btnNotasCleanFields);

		JPanel panelBoletim = new JPanel();
		panelBoletim.setFont(new Font("Tahoma", Font.BOLD, 14));
		tabbedPane.addTab("Boletim", null, panelBoletim, null);
		panelBoletim.setLayout(null);

		JLabel lblBoletimRgm = new JLabel("Informe o RGM:");
		lblBoletimRgm.setFont(new Font("Roboto", Font.PLAIN, 16));
		lblBoletimRgm.setBounds(10, 19, 119, 14);
		panelBoletim.add(lblBoletimRgm);

		JFormattedTextField formattedTextFieldBoletimRgm = new JFormattedTextField(new MaskFormatter(rgmMask));
		formattedTextFieldBoletimRgm.setFont(new Font("Roboto", Font.PLAIN, 16));
		formattedTextFieldBoletimRgm.setBounds(131, 14, 102, 25);
		panelBoletim.add(formattedTextFieldBoletimRgm);

		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setFont(new Font("Roboto", Font.PLAIN, 16));
		textArea.setBounds(10, 61, 644, 301);
		panelBoletim.add(textArea);

		// procurar por informações do aluno boletim
		JButton btnProcurar = new JButton("Procurar");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// limpar todos os campos em cada consulta
					textArea.setText(null);

					List<Aluno> alunoArr = new ArrayList<Aluno>();
					List<NotasFaltas> notasFaltasArr = new ArrayList<NotasFaltas>();

					alunoDAO = new AlunoDAO();
					notasFaltasDAO = new NotasFaltasDAO();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldBoletimRgm.getText().trim());

					// caso o campo de RGM esteja vazio
					if (formattedTextFieldBoletimRgm.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM!!");
						return;
					}

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					notasFaltasArr = notasFaltasDAO.listarNota(formattedTextFieldBoletimRgm.getText());

					// Verifica se a lista de notas e faltas está vazia
					if (notasFaltasArr.isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Não existem registros de notas ou faltas para este aluno. Crie um registro novo na aba de Notas e faltas");
						return;
					}

					textArea.append("----- Informações do aluno -----\n");
					for (Aluno aluno : alunoArr) {
						if (aluno.getRgm().equals(formattedTextFieldBoletimRgm.getText())) {
							textArea.append("RGM do aluno...  " + aluno.getRgm() + "\n");
							textArea.append("Nome do aluno...  " + aluno.getNome() + "\n");
							textArea.append("Curso do aluno...  " + aluno.getCurso() + "\n\n");
						}
					}

					textArea.append("-------------------------------------------------------------------\n\n");

					textArea.append("----- Notas do aluno -----\n");
					for (NotasFaltas notasFaltas : notasFaltasArr) {
						if (notasFaltas.getRgmAluno().equals(formattedTextFieldBoletimRgm.getText())) {
							textArea.append("Disciplina do aluno...  " + notasFaltas.getDisciplina() + "\n");
							textArea.append("Semestre do curso...  " + notasFaltas.getSemestre() + "\n");
							textArea.append("Nota da disciplina...  " + notasFaltas.getNota() + "\n");
							textArea.append("Faltas da disciplina...  " + notasFaltas.getFalta() + "\n\n");
						}
					}

					textArea.setCaretPosition(0);

					formattedTextFieldBoletimRgm.setText(null);
				} catch (Exception err) {
					System.err.println(err.getMessage());
				}
			}
		});
		btnProcurar.setFont(new Font("Roboto", Font.PLAIN, 16));
		btnProcurar.setBounds(265, 14, 107, 25);
		panelBoletim.add(btnProcurar);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAluno = new JMenu("Aluno");
		mnAluno.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		mnAluno.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnAluno);

		// salvar aluno
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					hasEmptyField = false;

					// validação nos campos de texto
					for (JTextField textField : textFields) {
						if (textField.getText().trim().isEmpty()) {
							hasEmptyField = true;
							errorMessage = "Preencha todos os campos (não esqueça da aba de cursos)";
							break;
						}
					}

					// validação nas combo box
					if (!hasEmptyField) {
						for (JComboBox comboBox : comboBoxes) {
							if (comboBox.getSelectedItem() == null) {
								hasEmptyField = true;
								errorMessage = "Selecione uma opção em todos os campos do curso";
								break;
							}
						}
					}

					// validação caso o campo de período esteja vazio
					if (!hasEmptyField && selectedPeriodo == null) {
						hasEmptyField = true;
						errorMessage = "Preencha o período em que você estuda";
					}

					// validação se há algum campo vazio
					if (hasEmptyField) {
						JOptionPane.showMessageDialog(null, errorMessage);
						return;
					}

					// validação tamanho dos campos
					for (Map.Entry<JTextField, String> campo : camposDoCurso.entrySet()) {
						if (campo.getKey().getText().trim().length() > 60) {
							JOptionPane.showMessageDialog(null,
									"O campo " + campo.getValue() + " deve conter no máximo 50 caracteres! ");
							return;
						}
					}

					// validação email é válido
					if (!formattedTextFieldEmail.getText().contains("@")
							|| !formattedTextFieldEmail.getText().contains(".")) {
						JOptionPane.showMessageDialog(null, "Insira um email válido! email@exemplo.com");
						return;
					}

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.saveAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

					formattedTextFieldRgm.setValue(null);
					formattedTextFieldNome.setText(null);
					formattedTextFieldEmail.setText(null);
					formattedTextFieldCelular.setValue(null);
					formattedTextFieldMunicipio.setText(null);
					formattedTextFieldEnd.setText(null);
					formattedTextFieldCpf.setValue(null);
					formattedTextFieldDataNascimento.setValue(null);
					comboCampus.setSelectedIndex(0);
					comboBoxCurso.setSelectedIndex(0);
					comboBoxUf.setSelectedIndex(0);
					radioBtnGroup.clearSelection();
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar o aluno: " + err.getMessage());
				}
			}
		});
		mntmSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		mntmSalvar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		mnAluno.add(mntmSalvar);

		// alterar aluno
		JMenuItem mntmAlterar = new JMenuItem("Alterar");
		mntmAlterar.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					hasEmptyField = false;

					// validação nos campos de texto
					for (JTextField textField : textFields) {
						if (textField.getText().trim().isEmpty()) {
							hasEmptyField = true;
							errorMessage = "Preencha todos os campos (não esqueça da aba de cursos)";
							break;
						}
					}

					// validação nas combo box
					if (!hasEmptyField) {
						for (JComboBox comboBox : comboBoxes) {
							if (comboBox.getSelectedItem() == null) {
								hasEmptyField = true;
								errorMessage = "Selecione uma opção em todos os campos do curso";
								break;
							}
						}
					}

					// validação caso o campo de período esteja vazio
					if (!hasEmptyField && selectedPeriodo == null) {
						hasEmptyField = true;
						errorMessage = "Preencha o período em que você estuda";
					}

					// validação se há algum campo vazio
					if (hasEmptyField) {
						JOptionPane.showMessageDialog(null, errorMessage);
						return;
					}

					// validação tamanho dos campos
					for (Map.Entry<JTextField, String> campo : camposDoCurso.entrySet()) {
						if (campo.getKey().getText().trim().length() > 60) {
							JOptionPane.showMessageDialog(null,
									"O campo " + campo.getValue() + " deve conter no máximo 50 caracteres! ");
							return;
						}
					}

					// validação email é válido
					if (!formattedTextFieldEmail.getText().contains("@")
							|| !formattedTextFieldEmail.getText().contains(".")) {
						JOptionPane.showMessageDialog(null, "Insira um email válido! email@exemplo.com");
						return;
					}

					// transformando string para data
					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
					Date dataDeNascimento = format.parse(formattedTextFieldDataNascimento.getText());

					viewAlunoMethods.updateAluno(formattedTextFieldRgm.getText(), formattedTextFieldNome.getText(),
							dataDeNascimento, formattedTextFieldCpf.getText(), formattedTextFieldEmail.getText(),
							formattedTextFieldEnd.getText(), formattedTextFieldMunicipio.getText(),
							comboBoxUf.getSelectedItem().toString(), formattedTextFieldCelular.getText(),
							comboBoxCurso.getSelectedItem().toString(), comboCampus.getSelectedItem().toString(),
							selectedPeriodo);

					formattedTextFieldRgm.setValue(null);
					formattedTextFieldNome.setText(null);
					formattedTextFieldEmail.setText(null);
					formattedTextFieldCelular.setValue(null);
					formattedTextFieldMunicipio.setText(null);
					formattedTextFieldEnd.setText(null);
					formattedTextFieldCpf.setValue(null);
					formattedTextFieldDataNascimento.setValue(null);
					comboCampus.setSelectedIndex(0);
					comboBoxCurso.setSelectedIndex(0);
					comboBoxUf.setSelectedIndex(0);
					radioBtnGroup.clearSelection();
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao alterar as informações do aluno: " + err.getMessage());
				}
			}
		});
		mntmAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmAlterar);

		// consultar aluno
		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.setFont(new Font("Roboto", Font.PLAIN, 12));
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

		// deletar aluno
		JMenuItem mntmExcluir = new JMenuItem("Excluir");
		mntmExcluir.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewAlunoMethods.deleteAluno(formattedTextFieldRgm.getText());

				formattedTextFieldRgm.setValue(null);
				formattedTextFieldNome.setValue(null);
				formattedTextFieldEmail.setValue(null);
				formattedTextFieldCelular.setValue(null);
				formattedTextFieldMunicipio.setValue(null);
				formattedTextFieldEnd.setValue(null);
				formattedTextFieldCpf.setValue(null);
				formattedTextFieldDataNascimento.setValue(null);
				comboCampus.setSelectedIndex(0);
				comboBoxCurso.setSelectedIndex(0);
				comboBoxUf.setSelectedIndex(0);
				radioBtnGroup.clearSelection();
			}
		});
		mntmExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		mnAluno.add(mntmExcluir);

		JSeparator separator_1 = new JSeparator();
		mnAluno.add(separator_1);

		// limpar os campos do aluno
		JMenuItem mntmNewMenuItem = new JMenuItem("Sair");
		mntmNewMenuItem.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				formattedTextFieldRgm.setValue(null);
				formattedTextFieldNome.setValue(null);
				formattedTextFieldEmail.setValue(null);
				formattedTextFieldCelular.setValue(null);
				formattedTextFieldMunicipio.setValue(null);
				formattedTextFieldEnd.setValue(null);
				formattedTextFieldCpf.setValue(null);
				formattedTextFieldDataNascimento.setValue(null);
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
		mnNotasFaltas.setFont(new Font("Roboto", Font.PLAIN, 12));
		menuBar.add(mnNotasFaltas);

		// salvar notas
		JMenuItem mntmNotasFaltasSalvar = new JMenuItem("Salvar");
		mntmNotasFaltasSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					// caso o campo de RGM esteja vazio
					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// Verifica se o valor de faltas é maior que 30 ou menor que 0
					if (Integer.parseInt(spinnerFaltas.getValue().toString()) > 30
							|| Integer.parseInt(spinnerFaltas.getValue().toString()) < 0) {
						JOptionPane.showMessageDialog(null,
								"O número de faltas não pode ser maior do que 30 e nem menor que 0.");
						return;
					}

					System.out.println("print" + spinnerFaltas.getValue());

					notasFaltasDAO = new NotasFaltasDAO();

					// verifica se a discplina e o semestre já existem para este aluno
					if (notasFaltasDAO.verificarDisciplinaSemestreExistente(formattedTextFieldRgmNotas.getText(),
							comboBoxCursoDisciplina.getSelectedItem().toString(),
							comboBoxSemestre.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "A disciplina e o semestre já existem para este aluno!");
						return;
					}

					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(spinnerFaltas.getValue().toString());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.salvar(notasFaltas);

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);

					JOptionPane.showMessageDialog(null, "Informações inseridas com sucesso!!");
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao salvar a nota: " + err.getMessage());
				}
			}
		});
		mntmNotasFaltasSalvar.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmNotasFaltasSalvar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasSalvar);

		// alterar notas
		JMenuItem mntmNotasFaltasAlterar = new JMenuItem("Alterar");
		mntmNotasFaltasAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// Verifica se o valor de faltas é maior que 30 ou menor que 0
					if (Integer.parseInt(spinnerFaltas.getValue().toString()) > 30
							|| Integer.parseInt(spinnerFaltas.getValue().toString()) < 0) {
						JOptionPane.showMessageDialog(null,
								"O número de faltas não pode ser maior do que 30 e nem menor que 0.");
						return;
					}

					notasFaltasDAO = new NotasFaltasDAO();

					notasFaltas = new NotasFaltas();

					notasFaltas.setDisciplina(comboBoxCursoDisciplina.getSelectedItem().toString());
					notasFaltas.setSemestre(comboBoxSemestre.getSelectedItem().toString());
					notasFaltas.setNota(comboNota.getSelectedItem().toString());
					notasFaltas.setFalta(spinnerFaltas.getValue().toString());
					notasFaltas.setRgmAluno(formattedTextFieldRgmNotas.getText());

					notasFaltasDAO.alterar(notasFaltas);

					JOptionPane.showMessageDialog(null, "Informações alteradas com sucesso!!");

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);
				} catch (Exception err) {
					System.err.println("Erro ao alterar nota:" + err.getMessage());
				}
			}
		});
		mntmNotasFaltasAlterar.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmNotasFaltasAlterar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		mntmNotasFaltasAlterar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasAlterar);

		// deletar notas
		JMenuItem mntmNotasFaltasExcluir = new JMenuItem("Excluir");
		mntmNotasFaltasExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					notasFaltasDAO = new NotasFaltasDAO();

					alunoDAO = new AlunoDAO();
					List<Aluno> alunoArr = new ArrayList<Aluno>();

					alunoArr = alunoDAO.listarAluno(formattedTextFieldRgmNotas.getText());

					// verificar se o aluno/rgm existe
					if (alunoArr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Não existe um aluno com esse RGM.");
						return; // para interromper o fluxo do método, caso o RGM seja inválido
					}

					if (formattedTextFieldRgmNotas.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha o campo de RGM");
						return;
					}

					// verifica se a disciplina e o semestre não existem para este aluno
					if (!notasFaltasDAO.verificarDisciplinaSemestreExistente(formattedTextFieldRgmNotas.getText(),
							comboBoxCursoDisciplina.getSelectedItem().toString(),
							comboBoxSemestre.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "A disciplina e o semestre não existem para este aluno!");
						return;
					}

					String rgm = formattedTextFieldRgmNotas.getText();
					String disciplina = comboBoxCursoDisciplina.getSelectedItem().toString();
					String semestre = comboBoxSemestre.getSelectedItem().toString();

					notasFaltasDAO.excluir(rgm, disciplina, semestre);

					JOptionPane.showMessageDialog(null, "Nota excluída com sucesso!");

					formattedTextFieldRgmNotas.setValue(null);
					formattedTextFieldNomeAluno.setText(null);
					formattedTextFieldCursoAluno.setText(null);
					spinnerFaltas.setValue(0);

					comboBoxCursoDisciplina.setSelectedIndex(0);
					comboBoxSemestre.setSelectedIndex(0);
					comboNota.setSelectedIndex(0);
				} catch (Exception err) {
					System.err.println("Ocorreu um erro ao deletar a nota: " + err.getMessage());
				}
			}
		});
		mntmNotasFaltasExcluir.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmNotasFaltasExcluir.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasExcluir);

		// consultar notas
		JMenuItem mntmNotasFaltasConsultar = new JMenuItem("Consultar");
		mntmNotasFaltasConsultar.addActionListener(new ActionListener() {
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
		mntmNotasFaltasConsultar.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmNotasFaltasConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		mnNotasFaltas.add(mntmNotasFaltasConsultar);

		JMenu mnAjuda = new JMenu("Ajuda");
		mnAjuda.setFont(new Font("Roboto Medium", Font.PLAIN, 12));
		menuBar.add(mnAjuda);

		// ver informações sobre o grupo
		JMenuItem mntmAjudaSobre = new JMenuItem("Sobre");
		mntmAjudaSobre.setFont(new Font("Roboto", Font.PLAIN, 12));
		mntmAjudaSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Projeto MVC - Cadastro de Aluno \n\n Integrantes do grupo: \n Gabriel Araujo de Jesus \n Gabriel Takuya Yamamoto");
			}
		});
		mntmAjudaSobre.setHorizontalAlignment(SwingConstants.CENTER);
		mnAjuda.add(mntmAjudaSobre);

	}
}
