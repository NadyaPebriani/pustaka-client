  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.nadyaperpustakaanclient.contoller;


import com.mycompany.nadyaperpustakaanclient.FormAnggota;
import com.mycompany.nadyaperpustakaanclient.model.Anggota;
import com.mycompany.nadyaperpustakaanclient.service.AnggotaService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author win 11
 */
public class AnggotaController {
    private final AnggotaService anggotaService;
    private final FormAnggota formAnggota;
    
    public AnggotaController(FormAnggota formAnggota){
        this.formAnggota = formAnggota;
        anggotaService = new AnggotaService();
    }
    
    public void bersihForm(){
        formAnggota.getTxtAnggotaId().setText("");
        formAnggota.getTxtNamaAnggota().setText("");
        formAnggota.getTxtAlmat().setText("");
    }
    
    public void getAnggotaId(){
        Long id =Long.parseLong(formAnggota.getTxtAnggotaId().getText());
        Anggota anggota = anggotaService.getAnggota(id);
        if(anggota!=null){
            formAnggota.getTxtNamaAnggota().setText(anggota.getNama());
            formAnggota.getTxtAlmat().setText(anggota.getAlamat());
        }else{
            JOptionPane.showMessageDialog(formAnggota,"Data Tidak Ada");
        }
    }
    
    public void saveAnggota(){
        Anggota anggota = new Anggota();
        anggota.setNama(formAnggota.getTxtNamaAnggota().getText());
        anggota.setAlamat(formAnggota.getTxtAlmat().getText());
        anggota = anggotaService.saveAnggota(anggota);
        if (anggota !=null){
            formAnggota.getTxtAnggotaId().setText(anggota.getAnggotaId().toString());
            JOptionPane.showMessageDialog(formAnggota, "Entri Data Berhasil");
           
        }else{
            JOptionPane.showMessageDialog(formAnggota, "Entri Data Gagal");
        }
    }
    
    public void viewTabel(){
        DefaultTableModel tabelModel = (DefaultTableModel) formAnggota.getTabelAnggoota().getModel();
        tabelModel.setRowCount(0);
        List<Anggota> anggotaList = anggotaService.getAllAnggota();
        for(Anggota anggota : anggotaList){
            Object[]row = {
                anggota.getAnggotaId(),
                anggota.getNama(),
                anggota.getAlamat()
            };
            tabelModel.addRow(row);
        }
    }
}
