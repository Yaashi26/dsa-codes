import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Stack;
import java.util.HashMap;
import java.util.Map;

public class CafeOrderSystemGUI extends JFrame {

    // --- Core Stack Data Structure and Logic ---
    private final Stack<MenuItem> orderStack; // The LIFO Stack
    private double currentTotal;
    
    // Menu items for selection
    private static final Map<String, MenuItem> MENU = new HashMap<>();
    static {
        MENU.put("Latte", new MenuItem("Latte", 4.50));
        MENU.put("Espresso", new MenuItem("Espresso", 3.00));
        MENU.put("Muffin", new MenuItem("Muffin", 2.75));
        MENU.put("Sandwich", new MenuItem("Sandwich", 6.50));
    }

    // --- GUI Components ---
    private final JTextArea orderListArea;
    private final JLabel totalLabel;
    private final JButton undoButton;
    private final JButton checkoutButton;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public CafeOrderSystemGUI() {
        // --- Initialization ---
        orderStack = new Stack<>();
        currentTotal = 0.0;
        
        // --- Frame Setup ---
        setTitle("Java Stack-based Cafe Order System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Outer layout
        
        // --- 1. Menu Panel (PUSH Action) ---
        JPanel menuPanel = new JPanel();
        menuPanel.setBorder(BorderFactory.createTitledBorder("Menu (Click to PUSH to Stack)"));
        menuPanel.setLayout(new GridLayout(MENU.size(), 1, 10, 10));
        
        for (MenuItem item : MENU.values()) {
            JButton itemButton = new JButton(item.toString());
            itemButton.setBackground(new Color(40, 167, 69)); // Green
            itemButton.setForeground(Color.WHITE);
            itemButton.addActionListener(new AddItemListener(item));
            menuPanel.add(itemButton);
        }
        add(menuPanel, BorderLayout.WEST);

        // --- 2. Order Display Panel ---
        JPanel orderPanel = new JPanel(new BorderLayout(5, 5));
        orderPanel.setBorder(BorderFactory.createTitledBorder("Current Order (Stack View)"));
        
        orderListArea = new JTextArea(10, 25);
        orderListArea.setEditable(false);
        orderPanel.add(new JScrollPane(orderListArea), BorderLayout.CENTER);

        // --- 3. Total and Actions Panel ---
        JPanel totalActionPanel = new JPanel(new BorderLayout());
        
        totalLabel = new JLabel("TOTAL: $0.00", SwingConstants.RIGHT);
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        totalLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        totalActionPanel.add(totalLabel, BorderLayout.NORTH);
        
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        undoButton = new JButton("Undo Last Item (POP)");
        undoButton.setBackground(new Color(255, 193, 7)); // Yellow/Orange
        undoButton.setForeground(Color.BLACK);
        undoButton.addActionListener(new UndoListener());
        
        checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(new Color(0, 123, 255)); // Blue
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.addActionListener(new CheckoutListener());

        actionButtonPanel.add(undoButton);
        actionButtonPanel.add(checkoutButton);
        
        totalActionPanel.add(actionButtonPanel, BorderLayout.SOUTH);
        orderPanel.add(totalActionPanel, BorderLayout.SOUTH);
        
        add(orderPanel, BorderLayout.CENTER);
        
        // --- Finalize Frame ---
        updateGUI();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * PUSH Operation: Adds an item to the stack.
     */
    private void pushItem(MenuItem item) {
        orderStack.push(item);
        currentTotal += item.getPrice();
        updateGUI();
    }
    
    /**
     * POP Operation: Removes the item most recently added (UNDO).
     */
    private void popLastItem() {
        if (!orderStack.isEmpty()) {
            MenuItem removedItem = orderStack.pop();
            currentTotal -= removedItem.getPrice();
            updateGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Order is empty. Nothing to undo!", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Updates all GUI elements to reflect the current stack state.
     */
    private void updateGUI() {
        orderListArea.setText("");
        
        // Display order items from TOP (last added) to BOTTOM (first added)
        if (orderStack.isEmpty()) {
            orderListArea.setText("  (Order is empty. Add an item.)");
            undoButton.setEnabled(false);
            checkoutButton.setEnabled(false);
        } else {
            // Iterate backward through the stack to show LIFO order
            for (int i = orderStack.size() - 1; i >= 0; i--) {
                MenuItem item = orderStack.get(i);
                String prefix = (i == orderStack.size() - 1) ? ">> (TOP) " : "   "; // Highlight the item to be POPPED
                orderListArea.append(prefix + item.toString() + "\n");
            }
            undoButton.setEnabled(true);
            checkoutButton.setEnabled(true);
        }

        // Update total
        totalLabel.setText("TOTAL: $" + df.format(currentTotal));
    }
    
    // --- Event Listeners for GUI Actions ---

    private class AddItemListener implements ActionListener {
        private final MenuItem item;

        public AddItemListener(MenuItem item) {
            this.item = item;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            pushItem(item);
        }
    }

    private class UndoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            popLastItem();
        }
    }

    private class CheckoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!orderStack.isEmpty()) {
                JOptionPane.showMessageDialog(CafeOrderSystemGUI.this, 
                    "Order placed!\nTotal Items: " + orderStack.size() + "\nFinal Total: $" + df.format(currentTotal), 
                    "Checkout Complete", JOptionPane.INFORMATION_MESSAGE);
                
                // Clear the stack and reset
                orderStack.clear();
                currentTotal = 0.0;
                updateGUI();
            }
        }
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> new CafeOrderSystemGUI());
    }
}
