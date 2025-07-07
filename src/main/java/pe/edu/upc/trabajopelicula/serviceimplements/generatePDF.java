package pe.edu.upc.trabajopelicula.serviceimplements;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.pdf.PdfWriter;
import pe.edu.upc.trabajopelicula.entities.Ticket;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;

public class generatePDF {

    public class PDFGenerator {

        public static void generatePDF(Ticket ticket, OutputStream outputStream) throws Exception {
            float width = 58 * 2.8346f;
            float height = 600f;
            float margin = 5f;

            Rectangle ticketSize = new Rectangle(width, height);
            Document document = new Document(ticketSize, margin, margin, margin, margin);
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // ENCABEZADO
            Paragraph header = new Paragraph();
            header.setAlignment(Element.ALIGN_CENTER);
            header.add(new Phrase("BOLETA ELECTRÓNICA\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
            header.add(new Phrase("B001-" + ticket.getId() + "\n", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 9)));
            header.setSpacingAfter(5f);
            document.add(header);

            // FECHA Y HORA
            String fecha = ticket.getFechapago().toString();
            String hora = java.time.LocalTime.now(ZoneId.of("America/Lima")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            var function = ticket.getFunctions_id();
            var user = function.getUser_id();
            var movieCinema = function.getMoviecinema_id();
            var movie = movieCinema.getMovies_id();
            var cinemaRoom = movieCinema.getCinemarooms_id();
            var cinema = cinemaRoom.getCinema();
            var sala = cinemaRoom.getRooms();

            Paragraph clientBlock = new Paragraph(
                    "Cliente: " + user.getName() + " " + user.getLastname() +
                            "\nUsuario: " + user.getUsername() +
                            "\nCine: " + cinema.getLocalname() +
                            "\nSala: " + sala.getNameroom() +
                            "\nPelícula: " + movie.getNamemovie() +
                            "\nClasificación: " + movie.getYearold() +
                            "\nDirector: " + movie.getDirector() +
                            "\nFecha: " + fecha +
                            "\nHora: " + hora,
                    FontFactory.getFont(FontFactory.HELVETICA, 7));
            clientBlock.setSpacingAfter(5f);
            document.add(clientBlock);

            Paragraph paymentType = new Paragraph("Tipo de pago: " + ticket.getTypepayments_id().getPaymentname(),
                    FontFactory.getFont(FontFactory.HELVETICA, 7));
            paymentType.setSpacingAfter(5f);
            document.add(paymentType);

            Paragraph total = new Paragraph("Total pagado: S/ " + String.format("%.2f", ticket.getTotalpay()),
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10));
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingBefore(5);
            document.add(total);

            // CÓDIGO QR
            try {
                String qrContent = generateQRContent(ticket);
                Image qrImage = generateQRCodeImage(qrContent, 120, 120);
                if (qrImage != null) {
                    qrImage.setAlignment(Element.ALIGN_CENTER);
                    qrImage.setSpacingBefore(10);
                    document.add(qrImage);
                }
            } catch (Exception e) {
                System.err.println("Error generando QR: " + e.getMessage());
            }

            document.close();
        }

        private static String generateQRContent(Ticket ticket) {
            return "Boleta B001-" + ticket.getId() +
                    "\nCliente: " + ticket.getFunctions_id().getUser_id().getUsername() +
                    "\nTotal: S/ " + String.format("%.2f", ticket.getTotalpay());
        }

        private static Image generateQRCodeImage(String text, int width, int height) throws Exception {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            BufferedImage qrBufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrBufferedImage, "PNG", baos);
            return Image.getInstance(baos.toByteArray());
        }
    }

}
