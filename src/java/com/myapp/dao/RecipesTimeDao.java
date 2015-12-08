/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.dao;

import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.myapp.model.Receta;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Karen
 */
@WebServlet(name = "BuscarRecetaServlet", urlPatterns = {"/BuscarRecetaServlet"})
public class RecipesTimeDao extends HttpServlet {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recogemos los valores que viene de la interfaz
        //String action = request.getParameter("action");
    }

    public static List<Receta> getReceipesTime() {
        List<Receta> list = new ArrayList<>();

        String log4jConfPath = "C:/Users/Karen/workspace/Jena/src/Tutorial/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        try {
            //opening owl file
            Model model = ModelFactory.createDefaultModel();
            model.read(new FileInputStream("C:/Users/Karen/Desktop/Proyecto/bbdd.owl"), null, "TTL");
            //System.out.println(model);
            Reasoner reasoner = ReasonerRegistry.getRDFSReasoner();

            InfModel inf = ModelFactory.createInfModel(reasoner, model);
            //create a new query
            String queryString
                    = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + " PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                    + " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                    + " PREFIX rec:<http://www.receta.org#>"
                    + " SELECT ?r ?cal ?tiempo ?dif (COUNT (distinct ?Ingrediente) as ?cantIng) (GROUP_CONCAT(DISTINCT ?modoPreparacion) as ?Preparacion) (GROUP_CONCAT(DISTINCT ?listaIngredientes) as ?listaIng)   "
                    + "  WHERE { "
                    + "  ?x rdf:type rec:Receta."
                    + "  ?x rdfs:label ?r."
                    + "  ?x rec:Ingrediente ?Ingrediente."
                    + "  ?x rec:modoPreparacion ?modoPreparacion."
                    + "  ?x rec:listaIngredientes ?listaIngredientes."
                    + "  ?x rec:Calorias ?cal."
                    + "  ?x rec:tiempoPreparacion ?tiempo."
                    + "  ?x rec:dificultad ?dif."
                    + "  } "
                    + " GROUP BY ?r ?cal ?tiempo ?dif"
                    + " ORDER BY ASC(?tiempo)";

            //System.out.println(queryString);
            com.hp.hpl.jena.query.Query q = QueryFactory.create(queryString);
            //execute the query and obtain results
            QueryExecution qe = QueryExecutionFactory.create(q, inf);
            ResultSet results = qe.execSelect();

            //print query results
            while (results.hasNext()) {

                //System.out.println(results.getResourceModel());
                //ResultSetFormatter.out(System.out,results, q);
                QuerySolution qs = results.next();

                Receta rec = new Receta();

                rec.setNombreReceta(qs.getLiteral("r"));

                Literal cantidadIngredientesLiteral = qs.getLiteral("cantIng");
                int cantidadIngredientes = cantidadIngredientesLiteral.getInt();
                rec.setCantidadIngredientes(cantidadIngredientes);

                rec.setIngredientes(qs.get("listaIng"));

                rec.setModoPreparacion(qs.get("Preparacion"));

                Literal tiempoLiteral = qs.getLiteral("tiempo");
                int tiempo = tiempoLiteral.getInt();
                rec.setTiempo(tiempo);

                Literal caloriasLiteral = qs.getLiteral("cal");
                int calorias = caloriasLiteral.getInt();
                rec.setCalorias(calorias);

                Literal dificultadLiteral = qs.getLiteral("dif");
                int dificultad = dificultadLiteral.getInt();
                rec.setDificultad(dificultad);

                list.add(rec);
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed !");
        }
        return list;
    }
}
