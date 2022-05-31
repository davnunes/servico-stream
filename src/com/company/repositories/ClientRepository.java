package com.company.repositories;

import com.company.domain.Client;

import java.util.*;

public class ClientRepository {

    private List<Client> clientsList = new ArrayList<>();

    public ClientRepository() {
        populateBase();
    }

    public void addClient(Client client) {
        clientsList.add(client);
        System.out.println("Client added");
    }

    public List<Client> findByName(String name) {
        List<Client> result = new ArrayList<>();
        for (Client client : clientsList) {
            String clientName = client.getName().toLowerCase();
            if (clientName.contains(name.toLowerCase())) {
                result.add(client);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No clients found");
            return new ArrayList<>();
        }

        return result;
    }

    public Client findByCPF(String cpf) {
        for (Client client : clientsList) {
            if (client.getCpf().equals(cpf)) {
                return client;
            }
        }

        System.out.println("No client found");
        return null;
    }

    public boolean updateClient(Client client, String cpf) {
        Client clientToUpdate = findByCPF(cpf);
        if (clientToUpdate == null) {
            return false;
        }

        if (isValidString(client.getName())) {
            clientToUpdate.setName(client.getName());
        }
        if (isValidString(client.getCpf())) {
            clientToUpdate.setCpf(client.getCpf());
        }
        if (isValidString(client.getEmail())) {
            clientToUpdate.setEmail(client.getEmail());
        }
        if (isValidString(client.getPhoneNumber())) {
            clientToUpdate.setPhoneNumber(client.getPhoneNumber());
        }
        if (client.getBirthDate() != null) {
            clientToUpdate.setBirthDate(client.getBirthDate());
        }

        System.out.println("Client updated");
        return true;
    }

    public boolean deleteClient(String cpf) {
        Client clientToDelete = findByCPF(cpf);
        if (clientToDelete == null) {
            return false;
        }

        clientsList.remove(clientToDelete);
        System.out.println("Client deleted");
        return true;
    }

    private boolean isValidString(String value) {
        return value != null && !value.isBlank();
    }

    private void populateBase() {
        // Cliente com pagamento pendente
        Client client1 = new Client("João", "Bosco", "20/10/1990",
                "11123456789", "joaobosco@gmail.com", "12345678912");

        // Cliente com pagamento em dia
        Client client2 = new Client("João", "Filho", "20/10/1970",
                "10389485967", "joaofilho@gmail.com", "12345670394");

        // Cliente com pagamento em dia
        Client client3 = new Client("Maria", "Sousa", "20/04/1983",
                "18395858937", "mariasousa@gmail.com", "10294858569");

        clientsList.add(client1);
        clientsList.add(client2);
        clientsList.add(client3);
    }
}
