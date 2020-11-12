package pMilionario;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.List;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class Main {
	static int count = 1;

	private static JTextField txtNome;
	static int ultimobottonepremuto, b50501;
	static boolean b5050;
	private static JTextField txtInserimentoDomanda;
	private static JTextField txtInserimentoA;
	private static JTextField txtInserimentoB;
	private static JTextField txtInserimentoC;
	private static JTextField txtInserimentoD;
	static int partita, ndomanda;
	static Timer timer = new Timer();

	public static void main(String[] args) {

		// Archivio delle domanda
		ArchivioR domande = new ArchivioR("domande.dat", Domande.getLrec(), Domande.getLpartita());

		// instanziamento gerry, giocatore e indici
		GerryScotti gerry = new GerryScotti("gerry\\");
		Giocatore giocatore = new Giocatore("");
		Indice i = new Indice(0);
		Indice bottoni = new Indice(0);

		// vettore del valore delle domande
		long valoredomande[] = { 500, 1000, 1500, 2000, 3000, 5000, 7000, 10000, 15000, 200000, 30000, 70000, 150000,
				300000, 1000000 };

		// Stringhe iniziali dei bottono delle risposte
		String a = "<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>A.</p></body></html>";
		String b = "<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>B.</p></body></html>";
		String c = "<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>C.</p></body></html>";
		String d = "<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>D.</p></body></html>";
		String gerrybox = " Benvenuto " + /* nome + */ " a Chi Vuol Essere Milionario!";

		// Stringhe di utilità per non mi ricordo cosa
		String x = "<html><head><link rel='stylesheet' type='text/css' href='http://pindaro.altervista.org/css/materialize.css'> </head><body style='text-align:center; font-size: 21px;'><p class='blue-text text-darken-2'>CREA <br> DOMANDA</p></body></html>";
		String y = "<html><body style='text-align:center; font-size: 21px;'><p>INIZIA A<br> GIOCARE</p></body></html>";

		// Stringhe di utilità per la stampa nelle label
		String html1Gerrybox = "<html><body style='text-align:center; font-size: 13px;color:#ffffff'><p>";
		String html2GerryBox = "</p></body></html>";

		String html1 = "<html><body style='text-align:center; font-size: 16px;color:#ffffff'><p>";
		String html2 = "</p></body></html>";

		String html1euro = "<html><body style='text-align:center; font-size: 16px;color:#ffffff'><p>";
		String html2euro = "\u20AC</p></body></html>";

		String labelbatteria = "Partita: ", labeldomanda = "N Domanda: ";

		// Frame
		JFrame frame = new JFrame("Il Milionario");
		frame.setBackground(new Color(0, 51, 102));
		frame.setBackground(new Color(0, 51, 102));
		frame.setForeground(new Color(255, 255, 255));
		frame.setForeground(new Color(255, 255, 255));
		frame.getContentPane().setLayout(null);
		frame.setBounds(300, 200, 716, 605);

		// secondo panel

		JPanel NuovaDomanda = new JPanel();
		NuovaDomanda.setBackground(new Color(0, 51, 102));
		NuovaDomanda.setBackground(new Color(0, 51, 102));
		NuovaDomanda.setForeground(new Color(255, 255, 255));
		NuovaDomanda.setForeground(new Color(255, 255, 255));
		NuovaDomanda.setLayout(null);
		frame.getContentPane().add(NuovaDomanda);

		JLabel lblConferm = new JLabel(
				"<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>Conferma</p></body></html>");
		lblConferm.setAlignmentY(1.0f);
		lblConferm.setBounds(381, 373, 118, 48);
		NuovaDomanda.add(lblConferm);
		JLabel lblTitoloInserimento = new JLabel("Inserimento di una nuova domanda");
		lblTitoloInserimento.setForeground(new Color(255, 255, 255));
		lblTitoloInserimento.setFont(new Font("Segoe UI Light", Font.BOLD, 29));
		lblTitoloInserimento.setBounds(189, 23, 523, 52);
		NuovaDomanda.add(lblTitoloInserimento);

		JLabel lblBatteria = new JLabel("Partita:");
		lblBatteria.setForeground(new Color(255, 255, 255));
		lblBatteria.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBatteria.setBounds(743, 23, 122, 28);
		NuovaDomanda.add(lblBatteria);

		JLabel lblNDomanda = new JLabel("N Domanda:");
		lblNDomanda.setForeground(Color.WHITE);
		lblNDomanda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNDomanda.setBounds(743, 62, 122, 28);
		NuovaDomanda.add(lblNDomanda);

		JLabel lblRispostaEsatta = new JLabel(
				"Clicca sui radiobutton accanto alle risposte per  selezionare quella esatta");
		lblRispostaEsatta.setForeground(Color.WHITE);
		lblRispostaEsatta.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRispostaEsatta.setBounds(163, 318, 575, 28);
		NuovaDomanda.add(lblRispostaEsatta);

		ButtonGroup rispostaesatta = new ButtonGroup();

		JRadioButton rdbtnA = new JRadioButton("");
		rdbtnA.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnA.setForeground(new Color(255, 255, 255));
		rdbtnA.setOpaque(false);
		rdbtnA.setBounds(75, 185, 40, 23);
		NuovaDomanda.add(rdbtnA);
		rispostaesatta.add(rdbtnA);

		JRadioButton rdbtnB = new JRadioButton("");
		rdbtnB.setBackground(new Color(255, 255, 255));
		rdbtnB.setBorder(new LineBorder(new Color(255, 140, 0)));
		rdbtnB.setOpaque(false);
		rdbtnB.setForeground(Color.WHITE);
		rdbtnB.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnB.setBounds(423, 185, 34, 23);
		NuovaDomanda.add(rdbtnB);
		rispostaesatta.add(rdbtnB);

		JRadioButton rdbtnC = new JRadioButton("");
		rdbtnC.setOpaque(false);
		rdbtnC.setForeground(Color.WHITE);
		rdbtnC.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnC.setBounds(75, 252, 40, 23);
		NuovaDomanda.add(rdbtnC);
		rispostaesatta.add(rdbtnC);

		JRadioButton rdbtnD = new JRadioButton("");
		rdbtnD.setOpaque(false);
		rdbtnD.setForeground(Color.WHITE);
		rdbtnD.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnD.setBounds(423, 252, 34, 23);
		NuovaDomanda.add(rdbtnD);
		rispostaesatta.add(rdbtnD);

		JLabel lbltornaIndietro = new JLabel(
				"<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>Indietro</p></body></html>");
		lbltornaIndietro.setAlignmentY(1.0f);
		lbltornaIndietro.setBounds(381, 432, 118, 48);
		NuovaDomanda.add(lbltornaIndietro);

		JButton btnIndietro = new JButton("");
		btnIndietro.setIcon(new ImageIcon("box.png"));
		btnIndietro.setToolTipText("");
		btnIndietro.setOpaque(false);
		btnIndietro.setContentAreaFilled(false);
		btnIndietro.setBorderPainted(false);
		btnIndietro.setBounds(266, 432, 320, 48);
		NuovaDomanda.add(btnIndietro);

		txtInserimentoDomanda = new JTextField();
		txtInserimentoDomanda.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		txtInserimentoDomanda.setOpaque(false);
		txtInserimentoDomanda.setForeground(Color.WHITE);
		txtInserimentoDomanda.setBounds(121, 101, 628, 48);
		NuovaDomanda.add(txtInserimentoDomanda);
		txtInserimentoDomanda.setColumns(10);

		txtInserimentoA = new JTextField();
		txtInserimentoA.setOpaque(false);
		txtInserimentoA.setForeground(Color.WHITE);
		txtInserimentoA.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		txtInserimentoA.setColumns(10);
		txtInserimentoA.setBounds(121, 171, 275, 48);
		NuovaDomanda.add(txtInserimentoA);

		txtInserimentoB = new JTextField();
		txtInserimentoB.setOpaque(false);
		txtInserimentoB.setForeground(Color.WHITE);
		txtInserimentoB.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		txtInserimentoB.setColumns(10);
		txtInserimentoB.setBounds(463, 171, 275, 48);
		NuovaDomanda.add(txtInserimentoB);

		txtInserimentoC = new JTextField();
		txtInserimentoC.setOpaque(false);
		txtInserimentoC.setForeground(Color.WHITE);
		txtInserimentoC.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		txtInserimentoC.setColumns(10);
		txtInserimentoC.setBounds(121, 238, 275, 48);
		NuovaDomanda.add(txtInserimentoC);

		txtInserimentoD = new JTextField();
		txtInserimentoD.setOpaque(false);
		txtInserimentoD.setForeground(Color.WHITE);
		txtInserimentoD.setFont(new Font("Segoe UI Light", Font.PLAIN, 21));
		txtInserimentoD.setColumns(10);
		txtInserimentoD.setBounds(463, 238, 275, 48);
		NuovaDomanda.add(txtInserimentoD);

		JButton btnConfermaInserimentoDomanda = new JButton("");

		btnConfermaInserimentoDomanda.setIcon(new ImageIcon("box.png"));
		btnConfermaInserimentoDomanda.setToolTipText("");
		btnConfermaInserimentoDomanda.setOpaque(false);
		btnConfermaInserimentoDomanda.setContentAreaFilled(false);
		btnConfermaInserimentoDomanda.setBorderPainted(false);
		btnConfermaInserimentoDomanda.setBounds(266, 373, 320, 48);
		NuovaDomanda.add(btnConfermaInserimentoDomanda);

		JButton btnA = new JButton();
		btnA.setIcon(new ImageIcon("box.png"));
		btnA.setOpaque(false);
		btnA.setContentAreaFilled(false);
		btnA.setBorderPainted(false);
		btnA.setBounds(99, 171, 320, 48);
		NuovaDomanda.add(btnA);
		btnA.setToolTipText("");

		JButton btnB = new JButton();
		btnB.setIcon(new ImageIcon("box.png"));
		btnB.setBounds(443, 171, 320, 48);
		NuovaDomanda.add(btnB);
		btnB.setOpaque(false);
		btnB.setContentAreaFilled(false);
		btnB.setBorderPainted(false);

		JButton btnDomanda = new JButton(
				"<html><body style='text-align:center; font-size: 18px;'><p></p></body></html>");
		btnDomanda.setOpaque(false);
		btnDomanda.setIcon(new ImageIcon("boxdomanda.png"));
		btnDomanda.setBackground(new Color(25, 25, 112));
		btnDomanda.setForeground(new Color(255, 255, 255));
		btnDomanda.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 26));
		btnDomanda.setBounds(99, 101, 664, 48);
		btnDomanda.setContentAreaFilled(false);
		btnDomanda.setBorderPainted(false);
		NuovaDomanda.add(btnDomanda);

		JButton btnC = new JButton();
		btnC.setIcon(new ImageIcon("box.png"));
		btnC.setOpaque(false);
		btnC.setContentAreaFilled(false);
		btnC.setBorderPainted(false);
		btnC.setBounds(99, 238, 320, 48);
		NuovaDomanda.add(btnC);

		JButton btnD = new JButton();
		btnD.setIcon(new ImageIcon("box.png"));
		btnD.setOpaque(false);
		btnD.setContentAreaFilled(false);
		btnD.setBorderPainted(false);
		btnD.setBounds(439, 238, 320, 48);
		NuovaDomanda.add(btnD);

		JLabel lblSfondo = new JLabel("");
		lblSfondo.setIcon(new ImageIcon("Milionario.jpg"));
		lblSfondo.setBounds(0, 0, 881, 497);
		NuovaDomanda.add(lblSfondo);
		NuovaDomanda.setBounds(0, 0, 891, 526);
		NuovaDomanda.setVisible(false);

		// Primo Panel

		JPanel Banca = new JPanel();
		Banca.setBackground(new Color(0, 51, 102));
		Banca.setBackground(new Color(0, 51, 102));
		Banca.setForeground(new Color(255, 255, 255));
		Banca.setForeground(new Color(255, 255, 255));
		Banca.setLayout(null);
		frame.getContentPane().add(Banca);

		JButton btnCreaDomanda = new JButton(x);
		btnCreaDomanda.setForeground(new Color(255, 255, 255));
		btnCreaDomanda.setBackground(new Color(51, 51, 102));
		btnCreaDomanda.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 17));
		btnCreaDomanda.setBounds(83, 289, 237, 178);
		Banca.add(btnCreaDomanda);
		JButton btnNuovoGioco = new JButton(y);

		btnNuovoGioco.setBackground(new Color(51, 51, 102));
		btnNuovoGioco.setForeground(new Color(255, 255, 255));
		btnNuovoGioco.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 26));
		btnNuovoGioco.setBounds(383, 289, 237, 178);
		Banca.add(btnNuovoGioco);

		JLabel lblTitolo = new JLabel("CHI VUOLE ESSERE MILIONARIO");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitolo.setBounds(10, 39, 680, 108);
		Banca.add(lblTitolo);
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setFont(new Font("Broadway", Font.PLAIN, 40));

		txtNome = new JTextField();
		txtNome.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 19));
		txtNome.setBounds(353, 147, 86, 31);
		Banca.add(txtNome);
		txtNome.setColumns(10);
		txtNome.setBackground(Color.WHITE);

		JLabel lblNome = new JLabel("Inserisci il tuo nome");
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome.setBounds(155, 147, 199, 34);
		Banca.add(lblNome);
		lblNome.setForeground(new Color(153, 102, 153));
		lblNome.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

		JLabel lblGerry = new JLabel("");
		lblGerry.setIcon(new ImageIcon(gerry.getGerryIntroduzione()));
		lblGerry.setBounds(0, 0, 700, 566);
		Banca.add(lblGerry);
		ButtonGroup opSq = new ButtonGroup();
		Banca.setVisible(true);
		Banca.setBounds(0, 0, 716, 605);

		// Terzo Frame

		JPanel Gioco = new JPanel();
		Gioco.setBackground(new Color(0, 51, 102));
		Gioco.setBackground(new Color(0, 51, 102));
		Gioco.setForeground(new Color(255, 255, 255));
		Gioco.setForeground(new Color(255, 255, 255));
		Gioco.setLayout(null);
		frame.getContentPane().add(Gioco);

		JLabel lblDomandaDisplay = new JLabel(
				"<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p></p></body></html>");
		lblDomandaDisplay.setBounds(119, 308, 591, 48);
		Gioco.add(lblDomandaDisplay);

		JButton btnrispA = new JButton("");
		JLabel lblA = new JLabel(a);
		lblA.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		lblA.setBounds(135, 364, 247, 48);
		Gioco.add(lblA);

		JLabel lblB = new JLabel(b);
		lblB.setBounds(478, 364, 247, 48);
		Gioco.add(lblB);

		JLabel lblC = new JLabel(c);
		lblC.setBounds(135, 423, 247, 48);
		Gioco.add(lblC);

		JLabel lblD = new JLabel(d);
		lblD.setBounds(478, 423, 247, 48);
		Gioco.add(lblD);

		JLabel lblgerry = new JLabel(
				"<html><body style='text-align:center; font-size: 10px;color:#ffffff'><p>Gerry:</p></body></html>");
		lblgerry.setForeground(Color.WHITE);
		lblgerry.setBounds(896, 11, 242, 48);
		Gioco.add(lblgerry);

		JLabel txtGerryBox = new JLabel();
		txtGerryBox.setForeground(Color.WHITE);
		txtGerryBox.setBounds(896, 43, 242, 89);
		Gioco.add(txtGerryBox);

		JButton btn5050 = new JButton();
		btn5050.setIcon(new ImageIcon("5050.png"));
		btn5050.setOpaque(false);
		btn5050.setContentAreaFilled(false);
		btn5050.setBorderPainted(false);
		btn5050.setBounds(630, 11, 64, 64);
		Gioco.add(btn5050);

		JButton btnPubblico = new JButton();
		btnPubblico.setIcon(new ImageIcon("pubblico.png"));
		btnPubblico.setOpaque(false);
		btnPubblico.setContentAreaFilled(false);
		btnPubblico.setBorderPainted(false);
		btnPubblico.setBounds(704, 11, 64, 64);
		Gioco.add(btnPubblico);

		JButton btnCasa = new JButton();
		btnCasa.setIcon(new ImageIcon("telefono.png"));
		btnCasa.setOpaque(false);
		btnCasa.setContentAreaFilled(false);
		btnCasa.setBorderPainted(false);
		btnCasa.setBounds(778, 11, 64, 64);
		Gioco.add(btnCasa);

		btnrispA.setIcon(new ImageIcon("box.png"));
		btnrispA.setOpaque(false);
		btnrispA.setContentAreaFilled(false);
		btnrispA.setBorderPainted(false);
		btnrispA.setBounds(99, 364, 320, 48);
		Gioco.add(btnrispA);
		btnrispA.setToolTipText("");

		JButton btnrispB = new JButton();
		btnrispB.setIcon(new ImageIcon("box.png"));
		btnrispB.setBounds(443, 364, 320, 48);
		Gioco.add(btnrispB);
		btnrispB.setOpaque(false);
		btnrispB.setContentAreaFilled(false);
		btnrispB.setBorderPainted(false);

		JButton btnDomandaDisplay = new JButton(
				"<html><body style='text-align:center; font-size: 18px;'><p></p></body></html>");
		btnDomandaDisplay.setOpaque(false);
		btnDomandaDisplay.setIcon(new ImageIcon("boxdomanda.png"));
		btnDomandaDisplay.setBackground(new Color(25, 25, 112));
		btnDomandaDisplay.setForeground(new Color(255, 255, 255));
		btnDomandaDisplay.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 26));
		btnDomandaDisplay.setBounds(99, 308, 664, 48);
		btnDomandaDisplay.setContentAreaFilled(false);
		btnDomandaDisplay.setBorderPainted(false);
		Gioco.add(btnDomandaDisplay);

		JButton btnrispC = new JButton();
		btnrispC.setIcon(new ImageIcon("box.png"));
		btnrispC.setOpaque(false);
		btnrispC.setContentAreaFilled(false);
		btnrispC.setBorderPainted(false);
		btnrispC.setBounds(99, 423, 320, 48);
		Gioco.add(btnrispC);

		JButton btnrispD = new JButton();
		btnrispD.setIcon(new ImageIcon("box.png"));
		btnrispD.setOpaque(false);
		btnrispD.setContentAreaFilled(false);
		btnrispD.setBorderPainted(false);
		btnrispD.setBounds(443, 423, 320, 48);
		Gioco.add(btnrispD);

		JLabel lblGerryMovimentato = new JLabel("");
		lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryLaAccendiamo()));
		lblGerryMovimentato.setBounds(0, 0, 886, 497);
		Gioco.add(lblGerryMovimentato);

		JLabel lblAzioneUtente = new JLabel(
				"<html><body style='text-align:center; font-size: 15px;color:#ffffff'><p>Inizio</p></body></html>");
		lblAzioneUtente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAzioneUtente.setBounds(932, 143, 181, 78);
		Gioco.add(lblAzioneUtente);

		JButton btnGiocatore = new JButton((String) null);
		btnGiocatore.setOpaque(false);
		btnGiocatore.setIcon(new ImageIcon("bottonegiocatore.png"));
		btnGiocatore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnGiocatore.setBounds(932, 143, 181, 78);
		Gioco.add(btnGiocatore);

		JLabel lblLabelSoldiVinti = new JLabel(
				"<html><body style='text-align:center; font-size: 15px;color:#ffffff'><p>Soldi vinti:</p></body></html>");
		lblLabelSoldiVinti.setBounds(918, 261, 220, 48);
		Gioco.add(lblLabelSoldiVinti);

		JLabel lblSoldiVinti = new JLabel(html1euro + 0 + html2euro);
		lblSoldiVinti.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSoldiVinti.setBounds(918, 322, 220, 48);
		Gioco.add(lblSoldiVinti);

		JLabel lblLabelValoreDomanda = new JLabel(
				"<html><body style='text-align:center; font-size: 15px;color:#ffffff'><p>Valore Domanda:</p></body></html>");
		lblLabelValoreDomanda.setBounds(918, 365, 220, 48);
		Gioco.add(lblLabelValoreDomanda);

		JLabel lblValoreDomanda = new JLabel(html1euro + 0 + html2euro);
		lblValoreDomanda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblValoreDomanda.setBounds(918, 423, 220, 48);
		Gioco.add(lblValoreDomanda);

		JLabel lblSfondo2 = new JLabel("");
		lblSfondo2.setIcon(new ImageIcon("Milionario.jpg"));
		lblSfondo2.setBounds(0, 0, 881, 497);

		JLabel lblSi = new JLabel(
				"<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>Si</p></body></html>");
		lblSi.setVisible(false);
		lblSi.setHorizontalAlignment(SwingConstants.CENTER);
		lblSi.setBounds(912, 143, 90, 78);
		Gioco.add(lblSi);

		JButton btnSi = new JButton((String) null);
		btnSi.setVisible(false);
		btnSi.setIcon(new ImageIcon("bottonegiocatore.png"));
		btnSi.setOpaque(false);
		btnSi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSi.setBounds(912, 143, 90, 78);
		Gioco.add(btnSi);

		JLabel lblNo = new JLabel(
				"<html><body style='text-align:center; font-size: 18px;color:#ffffff'><p>No</p></body></html>");
		lblNo.setVisible(false);
		lblNo.setHorizontalAlignment(SwingConstants.CENTER);
		lblNo.setBounds(1019, 143, 90, 78);
		Gioco.add(lblNo);

		JButton btnNo = new JButton((String) null);
		btnNo.setVisible(false);
		btnNo.setIcon(new ImageIcon("bottonegiocatore.png"));
		btnNo.setOpaque(false);
		btnNo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNo.setBounds(1019, 143, 90, 78);
		Gioco.add(btnNo);

		Gioco.add(lblSfondo2);
		Gioco.setBounds(0, 0, 1164, 526);
		Gioco.setVisible(false);
		frame.setVisible(true);

		// ActionListener di inizio Gioco

		btnGiocatore.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				lblA.setVisible(true);
				lblB.setVisible(true);
				lblC.setVisible(true);
				lblD.setVisible(true);
				
				btnrispA.setIcon(new ImageIcon("box.png"));
				btnrispB.setIcon(new ImageIcon("box.png"));
				btnrispC.setIcon(new ImageIcon("box.png"));
				btnrispD.setIcon(new ImageIcon("box.png"));
				btnrispA.setEnabled(true);
			   /* btnrispA.setDisabledSelectedIcon(btnrispA.getDisabledIcon());

				btnrispB.setEnabled(true);
			    btnrispB.setDisabledSelectedIcon(btnrispB.getDisabledIcon());

				btnrispC.setEnabled(true);
			    btnrispC.setDisabledSelectedIcon(btnrispC.getDisabledIcon());

				btnrispD.setEnabled(true);
			    btnrispD.setDisabledSelectedIcon(btnrispA.getDisabledIcon());*/


				lblA.setText(a);
				lblB.setText(b);
				lblC.setText(c);
				lblD.setText(d);
				lblDomandaDisplay.setText("");
				
				btnrispA.setEnabled(false);
				btnrispB.setEnabled(false);
				btnrispC.setEnabled(false);
				btnrispD.setEnabled(false);
				btnrispA.setDisabledIcon(btnrispA.getDisabledIcon());
				btnrispB.setDisabledIcon(btnrispB.getDisabledIcon());
				btnrispC.setDisabledIcon(btnrispC.getDisabledIcon());
				btnrispD.setDisabledIcon(btnrispD.getDisabledIcon());


				
				
				
				if(i.getI()==4)
					lblSoldiVinti.setText(html1 + valoredomande[4]);
				else if(i.getI()==9)
					lblSoldiVinti.setText(html1 + valoredomande[9]);


				Domande d = new Domande(domande.getR2(i.getI()));
				System.out.println(domande.getR2(i.getI()));

				// Timer
				new java.util.Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						System.out.println("Executed...");
						lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryDomanda()));
						lblDomandaDisplay.setText(html1 + d.getDomanda() + html2);
						lblValoreDomanda.setText(html1euro + valoredomande[i.getI()] + html2euro);
						if (i.getI() == 0)
							txtGerryBox.setText(html1Gerrybox + "Allora Iniziamo!" + html2GerryBox);
						else if (i.getI() == 6)
							txtGerryBox.setText(html1Gerrybox + "Siamo a metà vai così!" + html2GerryBox);
						else if (i.getI() == 9)
							txtGerryBox.setText(
									html1Gerrybox + "Decima domanda... Il gioco inizia a farsi duro eh" + html2GerryBox);
						else
							txtGerryBox.setText(html1Gerrybox + "Chissà qual è la prossima domanda..." + html2GerryBox);;

						timer.cancel();
						timer.purge();

					}
				}, 1000);

				Indice tempo = new Indice(0);
				timer = new Timer();

				new java.util.Timer().schedule(new TimerTask() {
					@Override
					public void run() {
						tempo.incremento();
						switch (tempo.getI()) {
						case 1:
							System.out.println("Executed...");
							lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPenserioso1()));
							lblA.setText(html1 + "A. " +d.getA() + html2);
							txtGerryBox.setText(html1Gerrybox + "Risposta A" + html2GerryBox);
							break;
						case 2:
							lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPenserioso2()));
							lblB.setText(html1 +"B. " + d.getB() + html2);
							txtGerryBox.setText(html1Gerrybox + "Risposta B" + html2GerryBox);
							break;
						case 3:
							lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPenserioso3()));
							lblC.setText(html1 + "C. " +d.getC() + html2);
							txtGerryBox.setText(html1Gerrybox + "Risposta C" + html2GerryBox);
							break;
						case 4:
							lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPenserioso4()));
							lblD.setText(html1 +"D. " + d.getD() + html2);
							txtGerryBox.setText(html1Gerrybox + "E l'ultima, la D" + html2GerryBox);
							break;

						case 5:
							timer.cancel();
							timer.purge();

						}
					}
				}, 2000,1500);

				lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPenserioso3()));
				txtGerryBox.setText(html1 + "Quale risposta scegli?" + html2);
				// Mostro la Domanda
				btnGiocatore.setEnabled(false);
				btnGiocatore.setDisabledIcon(btnGiocatore.getIcon());
				btnrispA.setEnabled(true);
				btnrispB.setEnabled(true);
				btnrispC.setEnabled(true);
				btnrispD.setEnabled(true);
				btn5050.setEnabled(true);
				btnCasa.setEnabled(true);
				btnPubblico.setEnabled(true);
			}
		});

		// action listener bottoni
		btnrispA.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (bottoni.getI() == 0) {
					bottoni.incremento();
					btnrispA.setIcon(new ImageIcon("bottoneaccendiamo.png"));
					txtGerryBox.setText(html1Gerrybox + "La accendiamo?" + html2GerryBox);
					btnNo.setVisible(true);
					lblNo.setVisible(true);
					btnSi.setVisible(true);
					lblSi.setVisible(true);
					btn5050.setEnabled(false);
					btn5050.setDisabledIcon(btn5050.getIcon());
					btnCasa.setEnabled(false);
					btnCasa.setDisabledIcon(btnCasa.getIcon());
					btnPubblico.setEnabled(false);
					btnPubblico.setDisabledIcon(btnPubblico.getIcon());
					lblAzioneUtente.setVisible(false);
					btnGiocatore.setVisible(false);
					ultimobottonepremuto = 1;

				}

			}
		});

		btnrispB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bottoni.getI() == 0) {
					bottoni.incremento();
					btnrispB.setIcon(new ImageIcon("bottoneaccendiamo.png"));
					txtGerryBox.setText(html1Gerrybox + "La accendiamo?" + html2GerryBox);
					btnNo.setVisible(true);
					lblNo.setVisible(true);
					btnSi.setVisible(true);
					lblSi.setVisible(true);
					btn5050.setEnabled(false);
					btn5050.setDisabledIcon(btn5050.getIcon());
					btnCasa.setEnabled(false);
					btnCasa.setDisabledIcon(btnCasa.getIcon());
					btnPubblico.setEnabled(false);
					btnPubblico.setDisabledIcon(btnPubblico.getIcon());
					lblAzioneUtente.setVisible(false);
					btnGiocatore.setVisible(false);
					ultimobottonepremuto = 2;

				}

			}
		});

		btnrispC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (bottoni.getI() == 0) {
					bottoni.incremento();
					btnrispC.setIcon(new ImageIcon("bottoneaccendiamo.png"));
					txtGerryBox.setText(html1Gerrybox + "La accendiamo?" + html2GerryBox);
					btnNo.setVisible(true);
					lblNo.setVisible(true);
					btnSi.setVisible(true);
					lblSi.setVisible(true);
					btn5050.setEnabled(false);
					btn5050.setDisabledIcon(btn5050.getIcon());
					btnCasa.setEnabled(false);
					btnCasa.setDisabledIcon(btnCasa.getIcon());
					btnPubblico.setEnabled(false);
					btnPubblico.setDisabledIcon(btnPubblico.getIcon());
					lblAzioneUtente.setVisible(false);
					btnGiocatore.setVisible(false);
					ultimobottonepremuto = 3;

				}
			}
		});

		btnrispD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (bottoni.getI() == 0) {
					bottoni.incremento();
					btnrispD.setIcon(new ImageIcon("bottoneaccendiamo.png"));
					txtGerryBox.setText(html1Gerrybox + "La accendiamo?" + html2GerryBox);
					btnNo.setVisible(true);
					lblNo.setVisible(true);
					btnSi.setVisible(true);
					lblSi.setVisible(true);
					btn5050.setEnabled(false);
					btn5050.setDisabledIcon(btn5050.getIcon());
					btnCasa.setEnabled(false);
					btnCasa.setDisabledIcon(btnCasa.getIcon());
					btnPubblico.setEnabled(false);
					btnPubblico.setDisabledIcon(btnPubblico.getIcon());
					lblAzioneUtente.setVisible(false);
					btnGiocatore.setVisible(false);
					ultimobottonepremuto = 4;

				}

			}
		});

		// Accendi Si
		btnSi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Domande d = new Domande(domande.getR2(i.getI()));
				bottoni.azzeraI();
				if (d.getRispostaesatta() == ultimobottonepremuto) {

					switch (ultimobottonepremuto) {
					case 1:
						btnrispA.setIcon(new ImageIcon("boxesatta.png"));
						break;
					case 2:
						btnrispB.setIcon(new ImageIcon("boxesatta.png"));
						break;
					case 3:
						btnrispC.setIcon(new ImageIcon("boxesatta.png"));
						break;
					case 4:
						btnrispD.setIcon(new ImageIcon("boxesatta.png"));
						break;
					}
					i.incremento();
					lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryRispostaGiusta()));
					btnGiocatore.setVisible(true);
					lblAzioneUtente.setVisible(true);
					btnGiocatore.setEnabled(true);
					btnNo.setVisible(false);
					lblNo.setVisible(false);
					btnSi.setVisible(false);
					lblSi.setVisible(false);
					if (i.getI() == 15) {
						txtGerryBox.setText(html1Gerrybox
								+ "Esattoo! E Vinceeeeeeeeeee e alla Pindaro diamo dieciiiiiiiii grandeee " + giocatore.getNome() + "!!!!!"
								+ html2GerryBox);
						lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryVincita()));
						btnGiocatore.setEnabled(false);
						btnrispA.setVisible(false);
						btnrispB.setVisible(false);
						btnrispC.setVisible(false);
						btnrispD.setVisible(false);
						btnDomanda.setVisible(false);
						lblA.setVisible(false);
						lblC.setVisible(false);
						lblD.setVisible(false);
						lblB.setVisible(false);
						lblDomandaDisplay.setVisible(false);
						btnDomandaDisplay.setVisible(false);
						btnGiocatore.setVisible(false);
					} else {
						txtGerryBox.setText(html1Gerrybox
								+ "Esattoo! Quando ti senti pronto per la prossima domanda premi il bottone sottostante"
								+ html2GerryBox);
						lblAzioneUtente.setText(html1 + "Continua" + html2);
						giocatore.setCheckpoint(lblLabelSoldiVinti.getText());
					}

				} else {
					switch (ultimobottonepremuto) {
					case 1:
						btnrispA.setIcon(new ImageIcon("boxsbagliata.png"));
						break;
					case 2:
						btnrispB.setIcon(new ImageIcon("boxsbagliata.png"));
						break;
					case 3:
						btnrispC.setIcon(new ImageIcon("boxsbagliata.png"));
						break;
					case 4:
						btnrispD.setIcon(new ImageIcon("boxsbagliata.png"));
						break;

					}
					lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryRispostaSbagliata()));
					btnGiocatore.setVisible(true);
					lblAzioneUtente.setVisible(true);
					btnGiocatore.setEnabled(true);
					lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPerdita()));
					txtGerryBox.setText(html1Gerrybox
							+ "Noo sarà per la prossima volta... Peccato che queste cose capitino una sola volta nella vita :^)"
							+ html2GerryBox);
					btnNo.setVisible(false);
					lblNo.setVisible(false);
					btnSi.setVisible(false);
					lblSi.setVisible(false);
					btnGiocatore.setEnabled(false);
					
					
					new java.util.Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							System.out.println("Executed...");
						
							frame.setVisible(false); //you can't see me!
							frame.dispose();
							timer.cancel();
							timer.purge();

						}
					}, 2000);
					

				}

			}
		});
		// Accendi no
		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				btnGiocatore.setVisible(true);
				lblAzioneUtente.setVisible(true);
				btnNo.setVisible(false);
				lblNo.setVisible(false);
				btnSi.setVisible(false);
				lblSi.setVisible(false);
				bottoni.azzeraI();
				btn5050.setEnabled(true);
				btnCasa.setEnabled(true);
				btnPubblico.setEnabled(true);
				//btnA.isSelected();
				//btnA.setDisabledSelectedIcon(btnA.getIcon());
				btnrispA.setIcon(new ImageIcon("box.png"));
				btnrispB.setIcon(new ImageIcon("box.png"));
				btnrispC.setIcon(new ImageIcon("box.png"));
				btnrispD.setIcon(new ImageIcon("box.png"));

			    


				

				}
		});

		// Action Listener per tornare alla home

		btnIndietro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Banca.setVisible(true);
				NuovaDomanda.setVisible(false);
				frame.setBounds(300, 200, 716, 605);
			}
		});

		// Action Listener per l'inizio di un nuovo gioco
		btnNuovoGioco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (txtNome.getText().equals(""))
					JOptionPane.showMessageDialog(null, "Inserire il nome");
				else {
					giocatore.setNome(txtNome.getText());
					frame.setBounds(300, 200, 1164, 526);
					txtGerryBox.setText(html1Gerrybox + "Benvenuto " + giocatore.getNome()
							+ " a chi vuole essere Milionario!" + html2GerryBox);
					lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryIntroduzione()));

					// aprire il frame di gioco
					Gioco.setVisible(true);
					Banca.setVisible(false);

					btnrispA.setEnabled(false);
					btnrispB.setEnabled(false);
					btnrispC.setEnabled(false);
					btnrispD.setEnabled(false);
					ImageIcon icon = new ImageIcon("box.png");
					btnrispA.setDisabledIcon(icon);
					btnrispB.setDisabledIcon(icon);
					btnrispC.setDisabledIcon(icon);
					btnrispD.setDisabledIcon(icon);
					btn5050.setEnabled(false);
					btn5050.setDisabledIcon(btn5050.getIcon());
					btnCasa.setEnabled(false);
					btnCasa.setDisabledIcon(btnCasa.getIcon());
					btnPubblico.setEnabled(false);
					btnPubblico.setDisabledIcon(btnPubblico.getIcon());

				}
			}
		});

		// Action Listener per l'inserimento di una nuova domanda
		btnCreaDomanda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Banca.setVisible(false);
				NuovaDomanda.setVisible(true);
				frame.setBounds(300, 200, 891, 526);
				int nrec = domande.numR();
				if (nrec > 0) {
					/*
					 * Domande d = new Domande(domande.getR(domande.numR() -
					 * 1)); for(int i=0;i<domande.numR();i++){ Domande x; x=new
					 * Domande(domande.getR(i));
					 * 
					 * System.out.println(x.getDomanda()+x.getA()+x.getB()+x.
					 * getC()+x.getD()+x.getCorretta()+x.getAiutocasa());
					 * System.out.println(x.toBuffer()); }
					 */

					partita = nrec / 15;
					ndomanda = nrec % 15;
				} else {
					partita = 0;
					ndomanda = 0;
				}

				lblBatteria.setText(labelbatteria + partita);
				lblNDomanda.setText(labeldomanda + ndomanda);
			}
		});

		// 5050
		btn5050.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				lblGerryMovimentato.setIcon(new ImageIcon(gerry.getGerryPerdita()));
				txtGerryBox.setText(html1Gerrybox + "Un Aiuto eeeeh" + html2GerryBox);

				Domande d = new Domande(domande.getR2(i.getI()));
				int rispostadatenere = d.CinquantaECinquanta();
				int rispostaesatta = d.getCorretta();

				b50501 = rispostadatenere;
				b5050 = true;
				lblA.setVisible(false);
				lblB.setVisible(false);
				lblC.setVisible(false);
				lblD.setVisible(false);
				btnrispA.setEnabled(false);
				btnrispB.setEnabled(false);
				btnrispC.setEnabled(false);
				btnrispD.setEnabled(false);

				switch (rispostadatenere) {
				case 1:
					lblA.setVisible(true);
					btnrispA.setEnabled(true);
					break;
				case 2:
					lblB.setVisible(true);
					btnrispB.setEnabled(true);

					break;
				case 3:
					lblC.setVisible(true);
					btnrispC.setEnabled(true);

					break;
				case 4:
					lblD.setVisible(true);
					btnrispD.setEnabled(true);

					break;
				}

				switch (rispostaesatta) {
				case 1:
					lblA.setVisible(true);
					btnrispA.setEnabled(true);
					break;
				case 2:
					lblB.setVisible(true);
					btnrispB.setEnabled(true);

					break;
				case 3:
					lblC.setVisible(true);
					btnrispC.setEnabled(true);

					break;
				case 4:
					lblD.setVisible(true);
					btnrispD.setEnabled(true);

					break;
				}

				btn5050.setEnabled(false);
				btn5050.setIcon(new ImageIcon("5050usato.png"));
				btn5050.setDisabledIcon(btn5050.getIcon());

			}
		});

		// aiutocasa
		btnCasa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Domande d = new Domande(domande.getR2(i.getI()));

				/*
				 * new java.util.Timer().schedule(new TimerTask() {
				 * 
				 * @Override public void run() {
				 * System.out.println("Executed...");
				 * 
				 * JOptionPane.showMessageDialog(null, "Chiamando a casa...");
				 * 
				 * timer.cancel(); timer.purge();
				 * JOptionPane.getRootFrame().dispose();
				 * 
				 * } }, 1000);
				 */
				JOptionPane.showMessageDialog(null, d.getAiutocasa());
				btnCasa.setIcon(new ImageIcon("telefonousato.png"));

			}
		});

		// pubblico
		btnPubblico.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Domande d = new Domande(domande.getR2(i.getI()));

				if (b5050)
					txtGerryBox.setText(html1Gerrybox + d.aiutoPubblico(b50501) + html2GerryBox);
				else
					txtGerryBox.setText(html1Gerrybox + d.aiutoPubblico() + html2GerryBox);

				btnPubblico.setIcon(new ImageIcon("pubblicousato.png"));
			}
		});

		// Action listener di inserimento domanda
		btnConfermaInserimentoDomanda.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtInserimentoA.getText().equals("") || txtInserimentoB.getText().equals("")
						|| txtInserimentoC.getText().equals("") || txtInserimentoD.getText().equals("")
						|| txtInserimentoDomanda.getText().equals("") || (!rdbtnA.isSelected() && !rdbtnB.isSelected()
								&& !rdbtnC.isSelected() && !rdbtnD.isSelected()))
					JOptionPane.showMessageDialog(null, "Compilare tutti i campi");
				else {

					String a, b, c, d;
					a = txtInserimentoA.getText();
					b = txtInserimentoB.getText();
					c = txtInserimentoC.getText();
					d = txtInserimentoD.getText();

					String domanda;
					domanda = txtInserimentoDomanda.getText();

					int corretta;

					if (rdbtnA.isSelected())
						corretta = 1;
					else if (rdbtnB.isSelected())
						corretta = 2;
					else if (rdbtnC.isSelected())
						corretta = 3;
					else
						corretta = 4;
					String aiutocasa = JOptionPane.showInputDialog("La casa che suggerimento da?");

					int n = domande.numR();

					if (ndomanda == 15) {
						ndomanda = 1;
						partita++;
					} else
						ndomanda++;
					lblBatteria.setText(labelbatteria + partita);
					lblNDomanda.setText(labeldomanda + ndomanda);

					Domande d2 = new Domande(domanda, a, b, c, d, corretta, aiutocasa, partita, ndomanda);

					domande.addR2(d2.toBuffer());

				}
			}
		});

	}

}
