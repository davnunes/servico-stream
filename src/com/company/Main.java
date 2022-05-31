package com.company;

import com.company.domain.Client;
import com.company.domain.Media;
import com.company.domain.Payment;
import com.company.repositories.ClientRepository;
import com.company.repositories.MediaRepository;
import com.company.repositories.PaymentRepository;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ClientRepository clientRepository = new ClientRepository();
    private static MediaRepository mediaRepository = new MediaRepository();
    private static PaymentRepository paymentRepository = new PaymentRepository();
    private static Scanner input = new Scanner(System.in);
    private static boolean working = true;
    private static int menu = -1;

    public static void main(String[] args) throws ParseException {
        while (working) {
            optionAccess();
        }
    }

    // ======================= Access Options =======================
    private static void getOption() {
        boolean aux = true;
        while (aux) {
            try {
                menu = input.nextInt();
                input.nextLine();
                aux = false;
            } catch (InputMismatchException exp) {
                System.out.println("Valor inválido, digite um número válido");
                input.nextLine();
            }
        }
    }

    private static void optionAccess() throws ParseException {
        System.out.println("<---------- Streaming ---------->");
        System.out.println("Escolha uma das opções:");
        System.out.println("1. Entrar como administrador");
        System.out.println("2. Entrar como cliente");
        System.out.println("3. Cadastrar novo cliente");
        System.out.println("0. Sair");

        getOption();

        switch (menu) {
            case 0:
                exit();
                break;
            case 1:
                accessAdmin();
                break;
            case 2:
                accessClient();
                break;
            case 3:
                addNewClient();
                break;
        }
    }

    private static void accessAdmin() throws ParseException {
        System.out.println("<---------- Streaming ---------->");
        System.out.println("Digite a senha (ou digite 'voltar' para retornar ao menu anterior):");
        String password = input.nextLine();
        if (password.equals("admin1234")) {
            optionAdmin();
        } else if (password.equals("voltar")) {
            optionAccess();
        } else {
            System.out.println("Senha inválida, digite novamente");
        }
    }

    private static void accessClient() throws ParseException {
        System.out.println("<---------- Streaming ---------->");
        System.out.println("Digite seu cpf (sem pontuação) (ou digite 'voltar' para retornar ao menu anterior):");
        String cpf = input.nextLine();
        if (cpf.equals("voltar")) {
            optionAccess();
        }

        Client client = clientRepository.findByCPF(cpf);
        if (client != null) {
            optionClient(client);
        }
    }

    private static void optionAdmin() {
        System.out.println("<---------- Streaming ---------->");
        System.out.println("Escolha uma das opções:");
        System.out.println("1. Cadastrar Mídia");
        System.out.println("2. Buscar Mídia");
        System.out.println("3. Deletar Mídia");
        System.out.println("4. Editar Mídia");
        System.out.println("5. Buscar Cliente");
        System.out.println("6. Deletar Cliente");
        System.out.println("0. Sair");

        getOption();

        switch (menu) {
            case 0:
                exit();
                break;
            case 1:
                addNewMedia();
                break;
            case 2:
                optionListMedia();
                break;
            case 3:
                deleteMedia();
                break;
            case 4:
                updateMedia();
                break;
            case 5:
                dataClient();
                break;
            case 6:
                deleteClient();
                break;
        }
        optionAdmin();
    }

    private static void optionClient(Client client) throws ParseException {
        System.out.println("<---------- Streaming ---------->");
        System.out.println("Escolha uma das opções:");
        if (paymentRepository.checkIfAllPaid(client.getCpf())) {
            System.out.println("1. Listar Filmes");
        }
        System.out.println("2. Fazer Pagamento");
        System.out.println("3. Editar Dados");
        System.out.println("4. Apagar Conta");
        System.out.println("0. Sair");

        getOption();

        switch (menu) {
            case 0:
                exit();
                break;
            case 1:
                if (paymentRepository.checkIfAllPaid(client.getCpf())) {
                    optionListMedia();
                } else {
                    System.out.println("Opção indisponível");
                    optionClient(client);
                }
                break;
            case 2:
                paymentsToPay(client);
                break;
            case 3:
                updateClient(client);
                break;
            case 4:
                clientRepository.deleteClient(client.getCpf());
                System.out.println("Conta apagada com sucesso");
        }
    }

    // ======================= CRUD Client =======================
    private static void addNewClient() {
        System.out.println("<---------- Streaming (Cadastro de Cliente) ---------->");
        System.out.println("Digite seu primeiro nome:");
        String name = input.nextLine();
        System.out.println("Digite seu sobrenome:");
        String surname = input.nextLine();
        System.out.println("Digite sua data de nascimento (xx/xx/xxxx):");
        String birthDate = input.nextLine();
        System.out.println("Digite seu telefone:");
        String phone = input.nextLine();
        System.out.println("Digite seu email:");
        String email = input.nextLine();
        System.out.println("Digite seu CPF (sem pontuação):");
        String cpf = input.nextLine();

        Client client = new Client(name, surname, birthDate, phone, email, cpf);

        clientRepository.addClient(client);
    }

    private static void updateClient(Client client) {
        System.out.println("<---------- Streaming (Editar Cliente) ---------->");
        System.out.println("Digite seu primeiro nome:");
        String name = input.nextLine();
        System.out.println("Digite seu sobrenome:");
        String surname = input.nextLine();
        System.out.println("Digite sua data de nascimento (xx/xx/xxxx):");
        String birthDate = input.nextLine();
        System.out.println("Digite seu telefone:");
        String phone = input.nextLine();
        System.out.println("Digite seu email:");
        String email = input.nextLine();
        System.out.println("Digite seu CPF (sem pontuação):");
        String cpf = input.nextLine();

        Client clientUpdated = new Client(name, surname, birthDate, phone, email, cpf);

        clientRepository.updateClient(clientUpdated, client.getCpf());
    }

    private static void deleteClient() {
        System.out.println("<---------- Streaming (Excluir Cliente) ---------->");
        Client client = dataClient();

        System.out.println("Deseja realmente excluir o cliente " + client.getName() + "? (S/N)");
        String answer = input.nextLine();
        if (answer.equals("S")) {
            clientRepository.deleteClient(client.getCpf());
        }
        optionAdmin();
    }

    private static Client dataClient() {
        System.out.println("Digite o CPF do cliente (sem pontuação):");
        String cpf = input.nextLine();
        Client client = clientRepository.findByCPF(cpf);

        if (client != null) {
            System.out.println("Dados atuais:");
            System.out.println("Nome: " + client.getName());
            System.out.println("Data de nascimento: " + client.getBirthDate());
            System.out.println("Telefone: " + client.getPhoneNumber());
            System.out.println("Email: " + client.getEmail());
            System.out.println("CPF: " + client.getCpf());
        }

        return client;
    }

    private static void paymentsToPay(Client client) throws ParseException {
        System.out.println("<---------- Streaming (Pagamentos a Pagar) ---------->");
        Payment payment = paymentRepository.findPaymentNotPaid(client.getCpf());
        if (payment == null) {
            System.out.println("Não há pagamentos a serem pagos.");
        } else {
            System.out.println(payment.toString());
            System.out.println("Selecione a forma de pagamento:");
            System.out.println("1. Cartão de crédito");
            System.out.println("2. Cartão de Débito");
            System.out.println("3. Boleto");
            System.out.println("4. Pré-pago");
            System.out.println("5. TED");
            System.out.println("6. PIX");
            System.out.println("0. Voltar");

            getOption();
            String formPayment = "";

            switch (menu) {
                case 0:
                    optionClient(client);
                case 1:
                    formPayment = "Cartão de crédito";
                    break;
                case 2:
                    formPayment = "Cartão de Débito";
                    break;
                case 3:
                    formPayment = "Boleto";
                    break;
                case 4:
                    formPayment = "Pré-pago";
                    break;
                case 5:
                    formPayment = "TED";
                    break;
                case 6:
                    formPayment = "PIX";
                    break;
            }

            paymentRepository.pay(payment, formPayment);
            optionClient(client);
        }
    }

    // ======================= CRUD Media =======================
    private static void optionListMedia() {
        System.out.println("<---------- Streaming (Buscar Mídia) ---------->");
        System.out.println("Escolha uma das opções:");
        System.out.println("1. Listar por Título");
        System.out.println("2. Listar por Gênero");
        System.out.println("3. Listar por Ano de Lançamento");
        System.out.println("4. Listar por Ator");
        System.out.println("5. Listar por Diretor");
        System.out.println("0. Sair");

        getOption();
        List<Media> mediaFound;

        switch (menu) {
            case 0:
                exit();
                break;
            case 1:
                System.out.println("Digite o título do filme:");
                String title = input.nextLine();
                mediaFound = mediaRepository.findMediaByTitle(title);
                choiceMedia(mediaFound);
                break;
            case 2:
                System.out.println("Digite o gênero do filme:");
                String genre = input.nextLine();
                mediaFound = mediaRepository.findMediaByGenre(genre);
                choiceMedia(mediaFound);
                break;
            case 3:
                System.out.println("Digite o ano de lançamento do filme:");
                int year = input.nextInt();
                mediaFound = mediaRepository.findMediaByYear(year);
                choiceMedia(mediaFound);
                break;
            case 4:
                System.out.println("Digite o nome do ator:");
                String actor = input.nextLine();
                mediaFound = mediaRepository.findMediaByActor(actor);
                choiceMedia(mediaFound);
                break;
            case 5:
                System.out.println("Digite o nome do diretor:");
                String director = input.nextLine();
                mediaFound = mediaRepository.findMediaByDirector(director);
                choiceMedia(mediaFound);
                break;
        }
        optionListMedia();
    }

    private static void choiceMedia(List<Media> mediaFound) {
        if (!mediaFound.isEmpty()) {
            System.out.println("Mídia(s) encontrada(s):");
            for (Media media : mediaFound) {
                System.out.println(media.toString());
            }
        }
    }

    private static void addNewMedia() {
        System.out.println("<---------- Streaming (Cadastro de Mídia) ---------->");
        System.out.println("Digite o título da mídia:");
        String title = input.nextLine();
        System.out.println("Digite o subtítulo da mídia:");
        String subtitle = input.nextLine();
        System.out.println("Digite o gênero da mídia:");
        String genre = input.nextLine();
        System.out.println("Digite o ano de lançamento da mídia:");
        int year = input.nextInt();
        System.out.println("Digite o nome do diretor:");
        String director = input.nextLine();
        System.out.println("Digite a categoria da mídia (Filme, Série, Documentário, etc):");
        String category = input.nextLine();
        System.out.println("Digite o nome do ator (Siga o exemplo: 'Ator 1, Ator 2, ...'):");
        String actor = input.nextLine();
        String[] listActors = actor.split(",");  // Separa os atores por ',' e add em um array

        Media media = new Media(title, subtitle, director, genre, listActors, year, category);

        mediaRepository.addMedia(media);
    }

    private static void updateMedia() {
        System.out.println("<---------- Streaming (Editar Mídia) ---------->");
        Media media = mediaData();

        System.out.println("Digite o título da mídia:");
        String title = input.nextLine();
        System.out.println("Digite o subtítulo da mídia:");
        String subtitle = input.nextLine();
        System.out.println("Digite o gênero da mídia:");
        String genre = input.nextLine();
        System.out.println("Digite o ano de lançamento da mídia:");
        int year = input.nextInt();
        System.out.println("Digite o nome do diretor:");
        String director = input.nextLine();
        System.out.println("Digite a categoria da mídia (Filme, Série):");
        String category = input.nextLine();
        System.out.println("Digite o nome do ator (Siga o exemplo: 'Ator 1 | Ator 2 | ...'):");
        String actor = input.nextLine();
        String[] listActors = actor.split("\\|");

        Media mediaUpdated = new Media(title, subtitle, director, genre, listActors, year, category);

        mediaRepository.updateMedia(mediaUpdated, media.getId());
    }

    private static void deleteMedia() {
        System.out.println("<---------- Streaming (Excluir Mídia) ---------->");
        Media media = mediaData();

        System.out.println("Deseja realmente excluir o mídia? (S/N)");
        String answer = input.nextLine();
        if (answer.equals("S")) {
            mediaRepository.deleteMedia(media.getId());
        } else {
            optionAdmin();
        }
    }

    private static Media mediaData() {
        System.out.println("Digite o ID da mídia:");
        String id = input.nextLine();
        Media media = mediaRepository.findMediaById(Integer.parseInt(id));

        if (media != null) {
            System.out.println("Dados atuais:");
            System.out.println("Título: " + media.getTitle());
            System.out.println("Subtítulo: " + media.getSubtitle());
            System.out.println("Gênero: " + media.getGenre());
            System.out.println("Ano de lançamento: " + media.getYear());
            System.out.println("Diretor: " + media.getDirector());
            System.out.println("Categoria: " + media.getCategory());
            System.out.println("Atores: " + media.getActorString());
        }

        return media;
    }

    // ======================= Other options =======================
    private static void exit() {
        System.out.println("Encerrando sistema...");
        input.close();
        Main.working = false;
    }
}
