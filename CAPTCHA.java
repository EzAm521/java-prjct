
import java.util.Scanner;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.util.Objects;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class CAPTCHA {
    public CAPTCHA() {}
    static String generateCaptcha(int n) {
        String chrs = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String captcha;
        int index;
        for(captcha = ""; n-->0; captcha = captcha + chrs.charAt(index)) {
            index = (int)(Math.random() * 62.0);
        }
        return captcha;
    }
    public String getCaptcha() {
        return generateCaptcha(5);
    }
    public Boolean jframe(String realc) {
        Scanner sc = new Scanner(System.in);
        JFrame frame = new JFrame("CAPTCHA");
        frame.setLayout((LayoutManager)null);
        frame.setVisible(true);
        frame.setBounds(100, 200, 400, 400);
        Container c = frame.getContentPane();
        JLabel obj1 = new JLabel(realc);
        obj1.setBounds(100, 100, 300, 30);
        obj1.setFont(new Font("Monospaced", 2, 30));
        c.add(obj1);
        String input = sc.nextLine();
        if (Objects.equals(input, realc)) {
            return true;
            //frame.setVisible(false);
        } else {
            System.out.println("Try again!");
            input = sc.nextLine();
            if (Objects.equals(input, realc)) {
                return true;
                //frame.setVisible(false);
            } else {
                System.out.println("Try again!");
                input = sc.nextLine();
                if (Objects.equals(input, realc)) {
                    return true;
                    //frame.setVisible(false);
                } else {
                    return false;
                }
            }
        }
    }
}