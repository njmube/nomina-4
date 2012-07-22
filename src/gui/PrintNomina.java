package gui;

import java.awt.*;
import java.awt.print.*;

public class PrintNomina implements Printable {

    String[] lines;

    PrintNomina(String s) {
        lines = s.split("\n");
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int index) throws PrinterException {
        int i = index * 63;
        if (lines.length - 1 < i) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        Font plain = new Font("Monospaced", Font.PLAIN, 10);
        Font bold = new Font("Monospaced", Font.BOLD, 10);
        g2d.setFont(plain);
        int maxi = i + 63 < lines.length ? i + 63 : lines.length;
        int height = g.getFontMetrics(plain).getHeight() - 2;
        int y = 0;
        while (i < maxi) {
            y += height;
            if (i % 9 == 8) {
                g.drawLine(10, y, Short.MAX_VALUE, y);
            } else {
                if(i%9==1||i+1==lines.length){
                    g2d.setFont(bold);
                }
                g.drawString(lines[i], 10, y);
                g2d.setFont(plain);
            }
            i++;
        }
        if (maxi==lines.length) {
            g.drawLine(10, y + 2*height, Short.MAX_VALUE, y + 2*height);
        }
        return PAGE_EXISTS;
    }
}
