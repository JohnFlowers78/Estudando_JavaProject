package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Rota;
import br.edu.up.entidades.Motorista; // Importe a classe Motorista

public class RotaPersistencia {

	public static boolean incluir(Rota rota) {
	    try {
	        EntityManager manager = EntityManagerFactory.getInstance();
	        manager.getTransaction().begin();
	        manager.persist(rota);

	        // Atualizar estatísticas de motoristas
	        Motorista motorista = rota.getMotorista();
	        if (motorista != null) {
	            motorista.setRota(rota);
	            manager.merge(motorista);
	        }

	        manager.getTransaction().commit();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

    public static boolean alterar(Rota rota) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.merge(rota);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Rota rota) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            rota = manager.merge(rota);
            manager.remove(rota);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Rota procurarPorId(int id) {
        EntityManager manager = EntityManagerFactory.getInstance();
        return manager.find(Rota.class, id);
    }

    @SuppressWarnings("unchecked")
	public static List<Rota> getRotas() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query query = manager.createQuery("SELECT r FROM Rota r");
        List<Rota> rotaList = query.getResultList();
        return rotaList;
    }

    public static boolean rotaExiste(int idRota) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query query = manager.createQuery("SELECT COUNT(r) FROM Rota r WHERE r.id = :idRota");
        query.setParameter("idRota", idRota);
        int count = ((Number) query.getSingleResult()).intValue();
        return count > 0;
    }
    
}
