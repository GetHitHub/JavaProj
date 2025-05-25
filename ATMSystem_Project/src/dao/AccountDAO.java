
package dao;

import model.Account;
import java.io.*;

public class AccountDAO {
    private final String FILE_PATH = "data/accounts.txt";

    public Account findAccount(String accNum, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(accNum) && parts[1].equals(password)) {
                    return new Account(parts[0], parts[1], Double.parseDouble(parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAccount(Account updatedAccount) {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("data/temp.txt");

        boolean updated = false;

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(updatedAccount.getAccountNumber())) {
                    writer.println(updatedAccount.toFileString());
                    updated = true;
                } else {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (updated) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
        }
        return updated;
    }
}
