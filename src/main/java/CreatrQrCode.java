import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@SuppressWarnings({ "unchecked", "rawtypes", "restriction" })
public class CreatrQrCode {

    /**
     * logoĬ�ϱ߿���ɫ WHITE
     */
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    /**
     * logoĬ�ϱ߿���
     */
    public static final int DEFAULT_BORDER = 1;
    /**
     * logo��СĬ��Ϊ��Ƭ��1/6
     */
    public static final int DEFAULT_LOGOPART = 4;
    /**
     * logoͼƬ�ĵ�ַ
     */
    public static String LogoPath = new File("").getAbsolutePath() + File.separator + "logo.png";
    /**
     * ��Ե
     */
    private final int border = DEFAULT_BORDER;
    /**
     * �Զ�����ɫ
     */
    private final Color borderColor;
    /**
     * �Զ���logo��С
     */
    private final int logoPart;
    /**
     * �ݴ���
     */
    public static final double RATE = 0.30d;
    /**
     * �ݴ��� ��ƽ L���ͣ� 7%�����ֿ��Ա��ָ��� M�����У� 15%�����ֿ��Ա��ָ��� Q�����ķ�֮һ��25%�����ֿ��Ա��ָ��� H�����ߣ�
     * 30%�����ֿ��Ա��ָ���
     *
     * Խ�����У��ˮƽ��Խ�ٵĴ洢����
     *
     */
    private static final ErrorCorrectionLevel E_RATE = ErrorCorrectionLevel.M;
    /**
     * ��ά���
     */
    public static final int QRCODE_WIDTH = 300;
    /**
     * ��ά��ߣ�Ĭ�Ͽ����ͬ��
     */
    public static final int QRCODE_HEIGHT = 200;
    /**
     * ��ά��ҳ�߾ָࣨ����������ʱҪʹ�õı߾࣬������Ϊ��λ��
     */
    public static final int QRCODE_MARGIN = 1;
    /**
     * ��ά��������ʹ���ַ�������
     */
    public static final String CHARACTER = "UTF-8";

    /**
     * ��ɫ�ϴ���һ��Ĭ������,���������ĺڰ����롣
     */
    public CreatrQrCode() {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public CreatrQrCode(Color borderColor, int logoPart) {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public int getBorder() {
        return border;
    }

    public int getLogoPart() {
        return logoPart;
    }

    /**
     * <p>
     * addLogo ����ά��ͼƬ���Logo
     * </p>
     *
     * @param qrPic
     *            ��ά���ļ�
     * @param logoPic
     *            logo�ļ�
     * @param creatrQrCode
     *            ���ɵĶ�ά��
     * @return boolean
     * @author XinLau
     * @since 2019��2��12������5:23:11
     */
    public static boolean addLogo(File qrPic, File logoPic, CreatrQrCode creatrQrCode) {
        try {
            if (!qrPic.isFile() || !logoPic.isFile()) {
                return false;
            }
            // ��ȡ��ά��ͼƬ����������ͼ����
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();
            // ��ȡLogoͼƬ
            BufferedImage logo = ImageIO.read(logoPic);
            // ���ֶ�ά���������ε�
            int widthLogo = image.getWidth() / creatrQrCode.getLogoPart();
            int heightLogo = image.getWidth() / creatrQrCode.getLogoPart();
            // ����ͼƬ����λ��
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            // ��ʼ����ͼƬ
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 10, 10);
            g.setStroke(new BasicStroke(creatrQrCode.getBorder()));
            g.setColor(creatrQrCode.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();
            ImageIO.write(image, "jpeg", qrPic);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    /**
//     * <p>
//     * pressText ��ά��ͼƬ�������
//     * </p>
//     *
//     * @param pressText
//     *            ��ά����Ҫ��ʾ������
//     * @param newImg
//     *            �����ֵ�ͼƬ
//     * @param targetImg
//     *            ��Ҫ������ֵ�ͼƬ
//     * @param fontStyle
//     *            ������ʽ
//     * @param color
//     *            ��ɫ
//     * @param fontSize
//     *            �����С
//     * @param width
//     *            ͼƬ���
//     * @param height
//     *            ͼƬ�߶�
//     * @return boolean
//     * @author XinLau
//     * @since 2019��2��12������5:31:50
//     */
//    public static boolean pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color,
//                                    int fontSize, int width, int height) {
//        // �������ֿ�ʼ��λ��
//        // x��ʼ��λ�ã��������Ҿ��У�����ͼƬ���-�����С*�ֵĸ�����/2
//        // int x = (width - (fontSize * pressText.length())) / 300;
//        int x = (width - (fontSize * pressText.length())) / 2;
//        // y��ʼ��λ�ã��������¾��У���ͼƬ�߶�-��ͼƬ�߶�-ͼƬ��ȣ�/2
//        int y = height - (height - width) / 3;
//        try {
//            File file = new File(targetImg);
//            Image src = ImageIO.read(file);
//            int imageWidth = src.getWidth(null);
//            int imageHeight = src.getHeight(null);
//            BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.createGraphics();
//            g.drawImage(src, 0, 0, imageWidth, imageHeight, null);
//            g.setColor(color);
//            g.setFont(new Font(null, fontStyle, fontSize));
//            g.drawString(pressText, x, y);
//            g.dispose();
//            FileOutputStream out = new FileOutputStream(newImg);
//            ImageIO.write(image, "JPEG", out);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image);
//            out.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    /**
     * <p>
     * createQrCode ���ɶ�ά�루��logo�������֣����Ҫ�ڶ�ά���·��������֣���ͼƬ����Ϊ�����Σ��ߴ��ڿ�
     * </p>
     *
     * @param qrcPath
     *            ����������ɵĶ�ά��ͼƬ
     * @param content
     *            ��ά���ʾ������
     * @param width
     *            ͼƬ�����Ŀ�
     * @param height
     *            ͼƬ�����ĸ�
     * @return boolean
     */
    public boolean createQrCode(String qrcPath, String content, int width, int height) {
        try {
            Path path = Paths.get(qrcPath);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>(3);
            // ����UTF-8�� ��ֹ��������
            hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
            // ���ö�ά�����ܰ�ɫ����Ĵ�С
            hints.put(EncodeHintType.MARGIN, QRCODE_MARGIN);
            // ���ö�ά����ݴ���
            hints.put(EncodeHintType.ERROR_CORRECTION, E_RATE);
            // ����ά�룬�ǵõ���multiFormatWriter.encode()ʱ���Ҫ����hints��������Ȼ����������Ч
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // ��ʼ����ά��
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
            // ��ά�����ɳɹ�
            return true;
        } catch (Exception e) {
            // ��ά������ʧ��
            return false;
        }
    }

    /**
     * <p>
     * createLogoQrCode ���ɶ�ά�루��logo�������֣�
     * </p>
     *
     * @param qrcPath
     *            ����������ɵĶ�ά��ͼƬ
     * @param content
     *            ��ά���ʾ������
     * @param width
     *            ͼƬ�����Ŀ�
     * @param height
     *            ͼƬ�����ĸ�
     * @param logoPath
     *            �����ڶ�ά���е�ͼƬ��logo��
     * @return boolean
     * @author XinLau
     * @since 2019��2��12������5:34:01
     */
    public boolean createLogoQrCode(String qrcPath, String content, int width, int height, String logoPath) {
        // �ж϶�ά���Ƿ����ɳɹ�
        if (createQrCode(qrcPath, content, width, height)) {
            // �ڶ�ά���м���ͼƬ
            CreatrQrCode creatrQrCode = new CreatrQrCode();
            File logoFile = new File(logoPath);
            File qrcFile = new File(qrcPath);
            // �ж�logo�Ƿ���ӳɹ�
            if (!addLogo(qrcFile, logoFile, creatrQrCode)) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

//    /**
//     * <p>
//     * createWordQrcode ���ɶ�ά�루��logo�������֣�
//     * </p>
//     *
//     * @param newImageWithText
//     *            ������ŵĴ������ֵĶ�ά��ͼƬ
//     * @param targetImage
//     *            ԭ��ά��ͼƬ
//     * @param text
//     *            ������ͼƬ�ϵ�������Ϣ
//     * @param width
//     *            ͼƬ��ȣ�������������x��ʼλ�ã�
//     * @param height
//     *            ͼƬ�߶ȣ�������������y��ʼλ�ã�
//     * @return boolean
//     * @author XinLau
//     * @since 2019��2��12������5:34:47
//     */
//    public boolean createWordQrcode(String newImageWithText, String targetImage, String text, int width, int height) {
//        // �ж϶�ά���Ƿ����ɳɹ�
//        if (createQrCode(newImageWithText, text, width, height)) {
//            // �����С
//            int font = 20;
//            // ������
//            int fontStyle = 4;
//            // �ڶ�ά���·�������֣��Ƿ��滻ԭͼ��
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(targetImage)) {
//                // ��ά��ͼƬ��������Ƿ�ɹ�
//                if (!pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, width, height)) {
//                    return false;
//                }
//            } else {
//                // ��ά��ͼƬ��������Ƿ�ɹ�
//                if (!pressText(text, newImageWithText, newImageWithText, fontStyle, Color.red, font, width, height)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

//    /**
//     * <p>
//     * createWordLogoQrcode ���ɶ�ά�루��logo�������֣�
//     * </p>
//     *
//     * @param newImageWithText
//     *            ������Ŵ������ֵĶ�ά��ͼƬ��λ��
//     * @param targetImage
//     *            ԭ��ά��ͼƬ
//     * @param text
//     *            �����ڶ�ά���ϵ�������Ϣ
//     * @param width
//     *            ͼƬ��ȣ�������������x��ʼλ�ã�
//     * @param height
//     *            ͼƬ�߶ȣ�������������y��ʼλ�ã�
//     * @param logoPath
//     *            �����ڶ�ά���ϵ�ͼƬ
//     * @return boolean
//     * @author XinLau
//     * @since 2019��2��12������5:35:27
//     */
//    public boolean createWordLogoQrcode(String newImageWithText, String targetImage, String text, int width, int height,
//                                        String logoPath) {
//        // �ж϶�ά���Ƿ����ɳɹ�
//        if (!createLogoQrCode(newImageWithText, text, width, height, logoPath)) {
//            return false;
//        }
//        // �����С
//        int font = 20;
//        // ������
//        int fontStyle = 4;
//        // �ڶ�ά���·�������֣��Ƿ��滻ԭͼ��
//        if (org.apache.commons.lang3.StringUtils.isNotBlank(targetImage)) {
//            if (!pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, width, height)) {
//                return false;
//            }
//        } else {
//            if (!pressText(text, newImageWithText, newImageWithText, fontStyle, Color.red, font, width, height)) {
//                return false;
//            }
//        }
//        return true;
//    }

    /**
     * <p>
     * readQrCode ������ά��
     * </p>
     *
     * @param path
     * @return Result
     * @author XinLau
     * @since 2019��2��12������5:36:03
     */
    public Result readQrCode(String path) {
        Result result = null;
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            // �����ά�����
            Map hints = new HashMap(1);
            hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
            // ��ȡ��ȡ��ά����
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            result = multiFormatReader.decode(binaryBitmap, hints);
        } catch (Exception e) {

        }
        return result;
    }

}
