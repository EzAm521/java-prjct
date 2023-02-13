
import java.util.Random;

class Password {

    
    private String login;
    private String password_text;

    public Random rand = new Random();

    String decipher(String message, int offset) {
        return cipher(message, 26 - (offset % 26));
    }



    String cipher(String message, int offset) {
        StringBuilder result = new StringBuilder();
        for (char character : message.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offset) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    

    Password(String text){

        setPassword(text);

    }

    

    Password(){

        password_text = "AntonMal123";

    }

    

    String getLogin(){return login;}
    String getPassword(){ return password_text; }
    private void setPassword(String text) {
        if (checkForSixLetters(text) && checkForNumber(text) && checkForCapital(text)) {
            password_text = text;

        } else password_text = "AntonMal123";
    }
    
    
    

    boolean checkForSixLetters(){

        return (password_text.length() >= 6);

    }

    

    boolean checkForSixLetters(String text){

        return (text.length() >= 6);

    }

    

    boolean checkForNumber(){

        for (int i = 0; i < password_text.length(); i++){

            if (password_text.charAt(i) >= '0' && password_text.charAt(i) <= '9'){

                return true;

            }

        }

        return false;

       

    }

    boolean checkForNumber(String text){

        for (int i = 0; i < text.length(); i++){

            if (text.charAt(i) >= '0' && text.charAt(i) <= '9'){

                return true;

            }

        }

        return false;

    }

    

    

    boolean checkForCapital(){
        return (password_text != password_text.toLowerCase());
    }

    boolean checkForCapital(String text){

        return (text != text.toLowerCase());

    }

}