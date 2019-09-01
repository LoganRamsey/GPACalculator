import java.awt.*;

public class AddtoPane {
	/**adding objects to container/pane, choosing layout and constraints**/
	public void addobjects(Component comp, Container yourcontainer, GridBagLayout gbl, GridBagConstraints gbc, int gridx, int gridy, int gridwidth, int gridheight){

        gbc.gridx = gridx;
        gbc.gridy = gridy;

        gbc.gridwidth = gridwidth;
        gbc.gridheight = gridheight;
        
        gbc.insets = new Insets(10,10,10,10);

        gbl.setConstraints(comp, gbc);
        yourcontainer.add(comp);
    }
} //https://stackoverflow.com//30656473/how-to-use-gridbaglayout - GridBag Layout basics and helper class 
