package com.hcy.todolistonbg;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 更换壁纸主类
 * 写得匆忙，没有注释
 *
 * ---使用前提
 *
 * 使用平台：windows10
 * 1.看根目录下【app.propetice】文件
 * 2.调节windows壁纸为幻灯片播放
 */
public class Main extends JFrame {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        ArrayList<String> data = new ArrayList<>();
        Main main = new Main();
        main.init();
        main.setLayout(null);
        List lists = new List();
        lists.setBounds(0,0,main.getWidth(),main.getHeight()/2);
        lists.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lists.setFont(new Font(null,Font.PLAIN,15));
        lists.add("今天热爱学习，热爱敲代码");
        JTextField textField = new JFormattedTextField("输入事件");
        textField.setBounds(0,main.getHeight()/2+20,main.getWidth(),30);
        textField.setBackground(Color.WHITE);
        JButton add = new JButton("添加");
        JButton delete = new JButton("删除");
        JButton save = new JButton("保存");
        add.setBounds(20,main.getHeight()/2+80,80,30);
        delete.setBounds(120,main.getHeight()/2+80,80,30);
        save.setBounds(220,main.getHeight()/2+80,80,30);
        JLabel label = new JLabel(LocalDate.now().toString());
        label.setBounds(0,main.getHeight()-80,80,30);
        main.add(lists);
        main.add(textField);
        main.add(add);
        main.add(label);
        main.add(delete);
        main.add(save);
        main.setVisible(true);


        add.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                if (text.length() == 0){
                    //为空
                    JOptionPane.showMessageDialog(main,"输入框为空哦");
                }else{
                    lists.add(text);
                    textField.setText("");
                }
            }
        });

        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = lists.getSelectedItem();
                int selectedIndex = lists.getSelectedIndex();
               if (selectedIndex != -1){
                   lists.remove(selectedIndex);
               }else{
                   //没有选择
                   JOptionPane.showMessageDialog(main,"选择删除项");
               }
            }
        });


        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] items = lists.getItems();
                data.clear();
                Arrays.stream(items).forEach(s -> {
                    data.add(s);
                });
                BgUtil.handleBg(data);
                JOptionPane.showMessageDialog(main,"壁纸将在一分钟之内更换");
            }
        });
    }

    void init() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        String lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
        UIManager.setLookAndFeel(lookAndFeel);
        this.setTitle("TODOLIST");
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
