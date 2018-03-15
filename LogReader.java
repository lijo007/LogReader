import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogReader extends JFrame {

	
	  private JTextField filename = new JTextField(), dir = new JTextField();

	  private JButton open = new JButton("Open"), save = new JButton("View");

	  public LogReader() {
	    JPanel p = new JPanel();
	    open.addActionListener(new OpenL());
	    p.add(open);
	    save.addActionListener(new SaveL());
	    p.add(save);
	    Container cp = getContentPane();
	    cp.add(p, BorderLayout.SOUTH);
	    dir.setEditable(false);
	    filename.setEditable(false);
	    p = new JPanel();
	    p.setLayout(new GridLayout(2, 1));
	    p.add(filename);
	    p.add(dir);
	    cp.add(p, BorderLayout.NORTH);
	  }

	  class OpenL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      JFileChooser c = new JFileChooser();
	      // Demonstrate "Open" dialog:
	      int rVal = c.showOpenDialog(LogReader.this);
	      if (rVal == JFileChooser.APPROVE_OPTION) {
	        filename.setText(c.getSelectedFile().getAbsolutePath());
	        dir.setText(c.getCurrentDirectory().toString());
	      }
	      if (rVal == JFileChooser.CANCEL_OPTION) {
	        filename.setText("You pressed cancel");
	        dir.setText("");
	      }
	    }
	  }

	  class SaveL implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	      // Demonstrate "Save" dialog:

	    	ReadData(filename.getText());
	    }
	  }

	  public static void main(String[] args) {
	    run(new LogReader(), 250, 110);
	  }

	  public static void run(JFrame frame, int width, int height) {
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(width, height);
	    frame.setVisible(true);
	  }
	  
	  public static void ReadData(String filePath){
		  
	        String line = "";
	        String lineSplitBy = ",";

	        File f = new File("source.htm");
            BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(f));
			
            bw.write("<html>");
            bw.write("<head><link  type=\"text/css\" href=\"https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css\" rel=\"stylesheet\"></link></head>");
            bw.write("<body>");
            bw.write("<h1>Logs</h1>");
            bw.write("<table id=\"myTable\" border=\"1\"><thead><tr><th>Sl No</th><th>Log Text</th></tr></thead><tbody>");
	        
	        BufferedReader br = new BufferedReader(new FileReader(filePath)); 
	        int lineCount=1;
	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] country = line.split(lineSplitBy);

	                String htmlLine= "<tr><td>" + lineCount + "</td><td>" + country[5] + "</td></tr>";
	                lineCount++;
	                bw.write(htmlLine);
	                bw.newLine();
	            }

	            bw.write("</tbody></table>");
	            bw.write("</body>");
	            bw.write("<script type=\"text/javascript\" charset=\"utf8\" src=\"https://code.jquery.com/jquery-3.3.1.min.js\" integrity=\"sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=\" crossorigin=\"anonymous\"></script>");
	            bw.write("<script type=\"text/javascript\" charset=\"utf8\" src=\"https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js\"></script>");
	            bw.write("<script>$(document).ready(function(){$('#myTable').DataTable();});</script>");
	            bw.write("</html>");

	            br.close();
	            bw.close();

	            Desktop.getDesktop().browse(f.toURI());
            
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	  }
	  
	}
