package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A dialog for selecting a profile photo from predefined sample images.
 */
public class PhotoSelectionPage extends JDialog {

    private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private String selectedImagePath;
    private boolean dialogCancelled = true;

    /**
     * Creates a PhotoSelectionPage dialog.
     * Initializes the dialog with a grid layout containing sample photo buttons.
     * Users can select a photo, and the selection is stored for further use.
     */
    public PhotoSelectionPage() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 3, 10, 10));

        String[] sampleImagePaths = {
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/a.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/b.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/c.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/d.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/e.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/f.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/g.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/h.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/k.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/l.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/m.jpg",
            "/Users/a.tahaekim/git/Comp132-Project/comp_project/src/profile photos/n.jpg"
        };

        for (String imagePath : sampleImagePaths) {
            addPhotoButton(imagePath);
        }

        JButton continueButton = new JButton("Continue");
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openNextPage();
                dispose();
            }
        });
        contentPane.add(continueButton);

        
    }

    /**
     * Adds a photo button to the dialog layout.
     * When clicked, the button sets the selectedImagePath.
     *
     * @param imagePath The path to the sample image for the button.
     */
    private void addPhotoButton(String imagePath) {
        JButton photoButton = new JButton();
        photoButton.setIcon(new ImageIcon(getScaledImage(new ImageIcon(imagePath).getImage(), new Dimension(100, 100))));
        photoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedImagePath = imagePath;
            }
        });

        contentPane.add(photoButton);
    }

    /**
     * Opens the next page (or takes appropriate action) based on the selected photo.
     * Displays a warning if no photo is selected.
     */
    private void openNextPage() {
        if (selectedImagePath != null) {
            dialogCancelled = false;
            JOptionPane.showMessageDialog(PhotoSelectionPage.this, "The profile photo is changed!");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a photo before continuing.", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Checks if the dialog was cancelled (closed without selecting a photo).
     *
     * @return True if the dialog was cancelled, false otherwise.
     */
    public boolean isDialogCancelled() {
        return dialogCancelled;
    }

    private Image getScaledImage(Image srcImg, Dimension size) {
        int originalWidth = srcImg.getWidth(null);
        int originalHeight = srcImg.getHeight(null);
        int boundWidth = size.width;
        int boundHeight = size.height;
        int newWidth = originalWidth;
        int newHeight = originalHeight;

        if (originalWidth > boundWidth || originalHeight > boundHeight) {
            double ratio = (double) originalWidth / (double) originalHeight;
            newWidth = (int) (boundHeight * ratio);
            newHeight = boundHeight;

            if (newWidth > boundWidth) {
                newWidth = boundWidth;
                newHeight = (int) (boundWidth / ratio);
            }
        }

        return srcImg.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    }

    /**
     * Gets the path of the selected image.
     *
     * @return The path of the selected image.
     */
    public String getSelectedImagePath() {
        return selectedImagePath;
    }
}