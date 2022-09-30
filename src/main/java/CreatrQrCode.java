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
     * logo默认边框颜色 WHITE
     */
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    /**
     * logo默认边框宽度
     */
    public static final int DEFAULT_BORDER = 1;
    /**
     * logo大小默认为照片的1/6
     */
    public static final int DEFAULT_LOGOPART = 4;
    /**
     * logo图片的地址
     */
    public static String LogoPath = new File("").getAbsolutePath() + File.separator + "logo.png";
    /**
     * 边缘
     */
    private final int border = DEFAULT_BORDER;
    /**
     * 自定义颜色
     */
    private final Color borderColor;
    /**
     * 自定义logo大小
     */
    private final int logoPart;
    /**
     * 容错率
     */
    public static final double RATE = 0.30d;
    /**
     * 容错率 电平 L（低） 7%的码字可以被恢复。 M级（中） 15%的码字可以被恢复。 Q级（四分之一）25%的码字可以被恢复。 H级（高）
     * 30%的码字可以被恢复。
     *
     * 越高误差校正水平，越少的存储容量
     *
     */
    private static final ErrorCorrectionLevel E_RATE = ErrorCorrectionLevel.M;
    /**
     * 二维码宽
     */
    public static final int QRCODE_WIDTH = 300;
    /**
     * 二维码高（默认宽高相同）
     */
    public static final int QRCODE_HEIGHT = 200;
    /**
     * 二维码页边距（指定生成条码时要使用的边距，以像素为单位）
     */
    public static final int QRCODE_MARGIN = 1;
    /**
     * 二维码内容所使用字符集编码
     */
    public static final String CHARACTER = "UTF-8";

    /**
     * 颜色上创建一个默认配置,生成正常的黑白条码。
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
     * addLogo 给二维码图片添加Logo
     * </p>
     *
     * @param qrPic
     *            二维码文件
     * @param logoPic
     *            logo文件
     * @param creatrQrCode
     *            生成的二维码
     * @return boolean
     * @author XinLau
     * @since 2019年2月12日下午5:23:11
     */
    public static boolean addLogo(File qrPic, File logoPic, CreatrQrCode creatrQrCode) {
        try {
            if (!qrPic.isFile() || !logoPic.isFile()) {
                return false;
            }
            // 读取二维码图片，并构建绘图对象
            BufferedImage image = ImageIO.read(qrPic);
            Graphics2D g = image.createGraphics();
            // 读取Logo图片
            BufferedImage logo = ImageIO.read(logoPic);
            // 保持二维码是正方形的
            int widthLogo = image.getWidth() / creatrQrCode.getLogoPart();
            int heightLogo = image.getWidth() / creatrQrCode.getLogoPart();
            // 计算图片放置位置
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            // 开始绘制图片
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
//     * pressText 二维码图片添加文字
//     * </p>
//     *
//     * @param pressText
//     *            二维码需要显示的文字
//     * @param newImg
//     *            带文字的图片
//     * @param targetImg
//     *            需要添加文字的图片
//     * @param fontStyle
//     *            文字样式
//     * @param color
//     *            颜色
//     * @param fontSize
//     *            字体大小
//     * @param width
//     *            图片宽度
//     * @param height
//     *            图片高度
//     * @return boolean
//     * @author XinLau
//     * @since 2019年2月12日下午5:31:50
//     */
//    public static boolean pressText(String pressText, String newImg, String targetImg, int fontStyle, Color color,
//                                    int fontSize, int width, int height) {
//        // 计算文字开始的位置
//        // x开始的位置（控制左右居中）：（图片宽度-字体大小*字的个数）/2
//        // int x = (width - (fontSize * pressText.length())) / 300;
//        int x = (width - (fontSize * pressText.length())) / 2;
//        // y开始的位置（控制上下居中）：图片高度-（图片高度-图片宽度）/2
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
     * createQrCode 生成二维码（无logo，无文字）如果要在二维码下方附上文字，把图片设置为长方形（高大于宽）
     * </p>
     *
     * @param qrcPath
     *            用来存放生成的二维码图片
     * @param content
     *            二维码表示的内容
     * @param width
     *            图片完整的宽
     * @param height
     *            图片完整的高
     * @return boolean
     */
    public boolean createQrCode(String qrcPath, String content, int width, int height) {
        try {
            Path path = Paths.get(qrcPath);
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            Map<EncodeHintType, Comparable> hints = new HashMap<EncodeHintType, Comparable>(3);
            // 设置UTF-8， 防止中文乱码
            hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
            // 设置二维码四周白色区域的大小
            hints.put(EncodeHintType.MARGIN, QRCODE_MARGIN);
            // 设置二维码的容错性
            hints.put(EncodeHintType.ERROR_CORRECTION, E_RATE);
            // 画二维码，记得调用multiFormatWriter.encode()时最后要带上hints参数，不然上面设置无效
            BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
            // 开始画二维码
            MatrixToImageWriter.writeToPath(bitMatrix, "jpg", path);
            // 二维码生成成功
            return true;
        } catch (Exception e) {
            // 二维码生成失败
            return false;
        }
    }

    /**
     * <p>
     * createLogoQrCode 生成二维码（有logo，无文字）
     * </p>
     *
     * @param qrcPath
     *            用来存放生成的二维码图片
     * @param content
     *            二维码表示的内容
     * @param width
     *            图片完整的宽
     * @param height
     *            图片完整的高
     * @param logoPath
     *            附加在二维码中的图片（logo）
     * @return boolean
     * @author XinLau
     * @since 2019年2月12日下午5:34:01
     */
    public boolean createLogoQrCode(String qrcPath, String content, int width, int height, String logoPath) {
        // 判断二维码是否生成成功
        if (createQrCode(qrcPath, content, width, height)) {
            // 在二维码中加入图片
            CreatrQrCode creatrQrCode = new CreatrQrCode();
            File logoFile = new File(logoPath);
            File qrcFile = new File(qrcPath);
            // 判断logo是否添加成功
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
//     * createWordQrcode 生成二维码（无logo，有文字）
//     * </p>
//     *
//     * @param newImageWithText
//     *            用来存放的带有文字的二维码图片
//     * @param targetImage
//     *            原二维码图片
//     * @param text
//     *            附加在图片上的文字信息
//     * @param width
//     *            图片宽度（用来计算文字x开始位置）
//     * @param height
//     *            图片高度（用来计算文字y开始位置）
//     * @return boolean
//     * @author XinLau
//     * @since 2019年2月12日下午5:34:47
//     */
//    public boolean createWordQrcode(String newImageWithText, String targetImage, String text, int width, int height) {
//        // 判断二维码是否生成成功
//        if (createQrCode(newImageWithText, text, width, height)) {
//            // 字体大小
//            int font = 20;
//            // 字体风格
//            int fontStyle = 4;
//            // 在二维码下方添加文字（是否替换原图）
//            if (org.apache.commons.lang3.StringUtils.isNotBlank(targetImage)) {
//                // 二维码图片添加文字是否成功
//                if (!pressText(text, newImageWithText, targetImage, fontStyle, Color.red, font, width, height)) {
//                    return false;
//                }
//            } else {
//                // 二维码图片添加文字是否成功
//                if (!pressText(text, newImageWithText, newImageWithText, fontStyle, Color.red, font, width, height)) {
//                    return false;
//                }
//            }
//        }
//        return true;
//    }

//    /**
//     * <p>
//     * createWordLogoQrcode 生成二维码（有logo，有文字）
//     * </p>
//     *
//     * @param newImageWithText
//     *            用来存放带有文字的二维码图片的位置
//     * @param targetImage
//     *            原二维码图片
//     * @param text
//     *            附加在二维码上的文字信息
//     * @param width
//     *            图片宽度（用来计算文字x开始位置）
//     * @param height
//     *            图片高度（用来计算文字y开始位置）
//     * @param logoPath
//     *            附加在二维码上的图片
//     * @return boolean
//     * @author XinLau
//     * @since 2019年2月12日下午5:35:27
//     */
//    public boolean createWordLogoQrcode(String newImageWithText, String targetImage, String text, int width, int height,
//                                        String logoPath) {
//        // 判断二维码是否生成成功
//        if (!createLogoQrCode(newImageWithText, text, width, height, logoPath)) {
//            return false;
//        }
//        // 字体大小
//        int font = 20;
//        // 字体风格
//        int fontStyle = 4;
//        // 在二维码下方添加文字（是否替换原图）
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
     * readQrCode 解析二维码
     * </p>
     *
     * @param path
     * @return Result
     * @author XinLau
     * @since 2019年2月12日下午5:36:03
     */
    public Result readQrCode(String path) {
        Result result = null;
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            File file = new File(path);
            BufferedImage image = ImageIO.read(file);
            // 定义二维码参数
            Map hints = new HashMap(1);
            hints.put(EncodeHintType.CHARACTER_SET, CHARACTER);
            // 获取读取二维码结果
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
            result = multiFormatReader.decode(binaryBitmap, hints);
        } catch (Exception e) {

        }
        return result;
    }

}
