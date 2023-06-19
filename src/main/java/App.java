import excel.Planilha;

import usuario.Usuario;
import util.PlanilhaUtil;
import util.SenderUtil;

import java.util.List;
import java.util.Scanner;

public class App {

    static boolean started = true;
    static Scanner scanner = new Scanner(System.in);

    static Usuario usuario = new Usuario();

    public static void main(String[] args) {

        do {
            System.out.print("Digite o email para ser utilizado: ");
            String email = scanner.nextLine();

            System.out.print("Digite o token para ser utilizado: ");
            String token = scanner.nextLine();

            usuario.setEmail(email);
            usuario.setToken(token);

            iniciarPrograma();
            verificarRepeticao();
        } while (started);
    }

    public static void verificarRepeticao() {
        System.out.println("Você quer enviar outra remessa? S/N");
        String resposta = scanner.nextLine();

        switch (resposta) {
            case "S":
                //clear;
                iniciarPrograma();
            case "N":
                scanner.close();
                started = false;
            default:
                iniciarPrograma();
        }
    }

    public static void iniciarPrograma() {

        try {
            System.out.print("Digite o caminho exato da planilha: ");
            String path = scanner.nextLine();

            System.out.print("Digite o nome do assunto: ");
            String assunto = scanner.nextLine();

            System.out.println("Digite a mensagem que irá ser enviada para todos: ");
            String mensagem = scanner.nextLine();

            List<Planilha> dadosPlanilha = PlanilhaUtil.getPlanilhas(path);

            for (Planilha planilha : dadosPlanilha) {

                String destinatario = planilha.getEmail();
                String anexo = planilha.getPath();

                SenderUtil.sendEmail(usuario.getEmail(), destinatario, usuario.getEmail(), usuario.getToken(), assunto, mensagem, anexo);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
