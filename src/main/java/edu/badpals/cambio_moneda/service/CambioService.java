package edu.badpals.cambio_moneda.service;

import edu.badpals.cambio_moneda.model.CambioDTO;
import edu.badpals.cambio_moneda.model.CambioData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class CambioService {

    @Autowired
    private RestTemplate restTemplate;

    private HashMap<String, String> monedas = new HashMap<>();

    private CambioService(){
        monedas.put("Euro", "EUR");
        monedas.put("Dólar USA", "USD");
        monedas.put("Libra esterlina", "GBP");
        monedas.put("Yen", "JPY");
    }

    public List<String> getNombresMonedas(){
        return monedas.keySet().stream().sorted().toList();
    }

    public String getCodigoMoneda(String nombre){
        return monedas.get(nombre);
    }

    private CambioData getCambioData(String base, String destino, Double amount) {
        String url;
        if(amount == null){
            url = "https://api.frankfurter.app/latest?from="+base+"&to="+destino;
        }else{
            url = "https://api.frankfurter.app/latest?from="+base+"&to="+destino+"&amount="+amount;
        }

        return restTemplate.getForObject(url, CambioData.class);
    }

    public CambioDTO cambiarMoneda(String base, String destino, String amount){
        String baseCodigo = getCodigoMoneda(base);
        String destinoCodigo = getCodigoMoneda(destino);
        Double amountDouble;
        try{
            amountDouble = Double.parseDouble(amount);
            if(amountDouble <= 0.0){
                amountDouble = 1.0;
            }
        } catch (NumberFormatException e) {
            amountDouble = 1.0;
        }
        Double resultadoCambio;
        Double tasa;
        String fecha;
        if(destinoCodigo.equals(baseCodigo)){
            resultadoCambio = amountDouble;
            tasa = 1.0;
            fecha = LocalDate.now().toString();
        }else{
            CambioData cambioData = getCambioData(baseCodigo, destinoCodigo, amountDouble);
            resultadoCambio = (Double) cambioData.getRates().values().iterator().next();
            tasa = amountDouble / resultadoCambio;
            fecha = cambioData.getDate();
        }

        return new CambioDTO(baseCodigo,destinoCodigo,amountDouble,tasa,fecha,resultadoCambio);
    }
}
