/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cr.ac.una.evacomunaws.service;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
@Stateless
@LocalBean
public class TarEvaluadorService {
   private static final Logger LOG = Logger.getLogger(TarEvaluadorService.class.getName());
    @PersistenceContext(unitName = "EvaComUNAPU")
    private EntityManager em; 
}
