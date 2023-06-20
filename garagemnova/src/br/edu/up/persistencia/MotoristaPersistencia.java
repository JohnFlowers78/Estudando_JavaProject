package br.edu.up.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.edu.up.entidades.Motorista;
import br.edu.up.entidades.Rota;

public class MotoristaPersistencia {

    public static boolean incluir(Motorista motorista) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            manager.persist(motorista);
            
            // Associar o motorista à rota, se existir
            Rota rota = motorista.getRota();
            if (rota != null) {
                rota.setMotorista(motorista);
                manager.merge(rota);
            }
            
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean alterar(Motorista motorista) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();
            
            // Associar o motorista à rota, se existir
            Rota rota = motorista.getRota();
            if (rota != null) {
                rota.setMotorista(motorista);
                manager.merge(rota);
            }
            
            manager.merge(motorista);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean excluir(Motorista motorista) {
        try {
            EntityManager manager = EntityManagerFactory.getInstance();
            manager.getTransaction().begin();

            // Desassociar a rota do motorista, se existir
            Rota rota = motorista.getRota();
            if (rota != null) {
                rota.setMotorista(null);
                manager.merge(rota);
            }

            manager.remove(motorista);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

	/*
	 * @SuppressWarnings("unchecked") public static Motorista procurarPorCPF(String
	 * cpf) { EntityManager manager = EntityManagerFactory.getInstance(); Query
	 * consulta = manager.createQuery("from Motorista where cpf = :param");
	 * consulta.setParameter("param", cpf); List<Motorista> motoristas =
	 * consulta.getResultList(); if (!motoristas.isEmpty()) { return
	 * motoristas.get(0); } return null; }
	 */

	/*
	 * @SuppressWarnings("unchecked") public static Motorista procurarPorNome(String
	 * nome) { EntityManager manager = EntityManagerFactory.getInstance(); Query
	 * consulta = manager.createQuery("from Motorista where nome = :param");
	 * consulta.setParameter("param", nome); List<Motorista> motoristas =
	 * consulta.getResultList(); if (!motoristas.isEmpty()) { return
	 * motoristas.get(0); } return null; }
	 */

    @SuppressWarnings("unchecked")
	public static Motorista procurarPorId(int id) {                        //   ESTUDAR COM CHAT
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Motorista where id = :param");
        consulta.setParameter("param", id);
        List<Motorista> motoristas = consulta.getResultList();
        if (!motoristas.isEmpty()) {
            return motoristas.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
	public static List<Motorista> getMotorista() {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query consulta = manager.createQuery("from Motorista");
        return consulta.getResultList();
    }
    
    

    public static boolean motoristaExiste(int idMotorista) {
        EntityManager manager = EntityManagerFactory.getInstance();
        Query query = manager.createQuery("SELECT COUNT(m) FROM Motorista m WHERE m.id = :idMotorista");
        query.setParameter("idMotorista", idMotorista);
        int count = ((Number) query.getSingleResult()).intValue();
        return count > 0;
    }
    
	/*
	 * public static int getTotalMotoristas() { EntityManager manager =
	 * EntityManagerFactory.getInstance(); Query consulta =
	 * manager.createQuery("select count(m) from Motorista m"); return ((Number)
	 * consulta.getSingleResult()).intValue(); }
	 */
     
}
