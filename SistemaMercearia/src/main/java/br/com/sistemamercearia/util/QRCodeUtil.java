/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.util;

/**
 *
 * @author gabriel
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;


public class QRCodeUtil {
    private static final int TAMANHO_PADRAO_QRCODE = 300;
    private static final String SEPARADOR_ESCRITA = ";";
    private static final DateTimeFormatter  FORMATO_DATA = DateTimeFormatter.ofPattern("yyy-MM-dd");
    
    public static BufferedImage gerarQRCode(Map<String, Object> dados) throws Exception {
        String textoCodificado = formatarTextoQRCode(dados);
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        BitMatrix bitMatrix = qrCodeWriter.encode(
                textoCodificado,
                BarcodeFormat.QR_CODE,
                TAMANHO_PADRAO_QRCODE,
                TAMANHO_PADRAO_QRCODE
        );
        
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }
    
    public static Map<String, Object> decodificarQRCode(BufferedImage imagem) throws Exception{
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(imagem);
        HybridBinarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        
        Result resultado = new MultiFormatReader().decode(bitmap);
        String textoCodificado = resultado.getText();
        
        String[] partes = textoCodificado.split(SEPARADOR_ESCRITA);
        
        if(partes.length < 2)
            throw new IllegalArgumentException("Formato inválido");
        
        Map<String, Object> dados = new HashMap<>();
        dados.put("lote", partes[0]);
        dados.put("validade", partes[1]);
        
        return dados;
        
    }
    
    public static void salvarQRCodeEmArquivo(Map<String, Object> dados, String caminhoArquivo) throws Exception {
        String textoCodificado = formatarTextoQRCode(dados);
        
        com.google.zxing.qrcode.QRCodeWriter qrCodeWriter = new com.google.zxing.qrcode.QRCodeWriter();
        com.google.zxing.common.BitMatrix bitMatrix = qrCodeWriter.encode(
            textoCodificado, 
            com.google.zxing.BarcodeFormat.QR_CODE, 
            TAMANHO_PADRAO_QRCODE, 
            TAMANHO_PADRAO_QRCODE
        );
        
        File arquivo = new File(caminhoArquivo);
        com.google.zxing.client.j2se.MatrixToImageWriter.writeToPath(bitMatrix, "PNG", arquivo.toPath());
    }
    
    private static String formatarTextoQRCode(Map<String, Object> dados){
        String lote = (String) dados.get("lote");
        LocalDate dataDeValidade = (LocalDate) dados.get("validade");
        
        if(lote == null || dataDeValidade == null)
            throw new IllegalArgumentException("A tabela hash deve conter as chaves 'lote' e 'validade'.");
        
        return lote + SEPARADOR_ESCRITA + dataDeValidade.format(FORMATO_DATA);
    }
}
