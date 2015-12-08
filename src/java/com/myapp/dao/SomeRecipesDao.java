/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myapp.dao;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;
import com.myapp.model.Receta;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.PropertyConfigurator;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author Karen
 */
@WebServlet(name = "BuscarRecetaServlet", urlPatterns = {"/BuscarRecetaServlet"})
public class SomeRecipesDao extends HttpServlet {

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recogemos los valores que viene de la interfaz
        //String action = request.getParameter("action");
    }

    public static String convertToSPARQLListQuery(List<String> list) {
        StringBuffer sb = new StringBuffer();
        // sb.append("(");
        for (String item : list) {
            sb.append("rec:" + item);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2); // remove last comma and whitespace
        //sb.append(")");
        return sb.toString();
    }

    public static String convertToSPARQLListQuery2(List<String> list) {
        StringBuffer sb = new StringBuffer();
        sb.append(" ");
        for (String item : list) {
            sb.append("rec:" + item);
            sb.append(", ");
        }
        sb.setLength(sb.length() - 2); // remove last comma and whitespace
        sb.append(" ");
        return sb.toString();
    }

    public static List<Receta> getSomeReceipes(List ing) {
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
            //create a new query. Te saca las recetas con esos ingredientes y menos
            String queryString
                    = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"
                    + " PREFIX owl: <http://www.w3.org/2002/07/owl#>"
                    + " PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
                    + " PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"
                    + " PREFIX rec:<http://www.receta.org#>"
                    + " SELECT ?r ?cal ?tiempo ?dif (count(distinct ?Ingrediente) as ?oi) (GROUP_CONCAT(DISTINCT ?modoPreparacion) as ?Preparacion) (GROUP_CONCAT(DISTINCT ?listaIngredientes) as ?listaIng) {"
                    + "  ?x rdfs:label ?r."
                    + "  ?x rec:Ingrediente ?Ingrediente. "
                    + "  ?x rec:modoPreparacion ?modoPreparacion."
                    + "  ?x rec:listaIngredientes ?listaIngredientes."
                    + "  ?x rec:Calorias ?cal."
                    + "  ?x rec:tiempoPreparacion ?tiempo."
                    + "  ?x rec:dificultad ?dif."
                    + "   filter not exists {"
                    + "     ?x rec:Ingrediente ?other_ingredient "
                    + "    filter( ?other_ingredient not in ( " + convertToSPARQLListQuery(ing) + " ) )"
                    + " }"
                    + " } "
                    + " GROUP BY ?r ?cal ?tiempo ?dif"
                    + " order by ASC(count(distinct ?Ingrediente))";
            System.out.println(queryString);
            com.hp.hpl.jena.query.Query q = QueryFactory.create(queryString);
            //execute the query and obtain results
            QueryExecution qe = QueryExecutionFactory.create(q, inf);
            ResultSet results = qe.execSelect();

            //print query results
            while (results.hasNext()) {
                //System.out.println(results.getResourceModel());
                //ResultSetFormatter.out(System.out,results, q);
                Receta rec = new Receta();
                QuerySolution qs = results.next();

                Literal dificultadLiteral = qs.getLiteral("dif");
                int dificultad = dificultadLiteral.getInt();
                rec.setDificultad(dificultad);

                Literal tiempoLiteral = qs.getLiteral("tiempo");
                int tiempo = tiempoLiteral.getInt();
                rec.setTiempo(tiempo);

                Literal caloriasLiteral = qs.getLiteral("cal");
                int calorias = caloriasLiteral.getInt();
                rec.setCalorias(calorias);

                rec.setModoPreparacion(qs.get("Preparacion"));

                rec.setIngredientes(qs.get("listaIng"));

                Literal cantidadIngredientesLiteral = qs.getLiteral("oi");
                int cantidadIngredientes = cantidadIngredientesLiteral.getInt();
                rec.setCantidadIngredientes(cantidadIngredientes);

                Literal nombreRecetaLiteral = qs.getLiteral("r");
                Object nombreReceta = nombreRecetaLiteral.getString();
                System.out.println("jaaaaaa" + nombreReceta);
                rec.setNombreReceta(qs.getLiteral("r"));

                list.add(rec);
                if (list.contains(rec)) {
                    list.remove(rec);
                }
            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed !");
        }

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
                    + " SELECT ?r ?cal ?tiempo ?dif (COUNT (?i) as ?cantIng) (GROUP_CONCAT(DISTINCT ?listaIngredientes) as ?listaIng)  (GROUP_CONCAT(DISTINCT ?modoPreparacion) as ?Preparacion) "
                    + "  WHERE { "
                    + "   ?x rdf:type rec:Receta ."
                    + "   ?x rdfs:label ?r."
                    + "   ?x rec:Ingrediente" + convertToSPARQLListQuery2(ing) + "."
                    + "   ?x rec:Ingrediente ?i."
                    + "  ?x rec:Calorias ?cal."
                    + "  ?x rec:tiempoPreparacion ?tiempo."
                    + "  ?x rec:dificultad ?dif."
                    + "  ?x rec:listaIngredientes ?listaIngredientes."
                    + "  ?x rec:modoPreparacion ?modoPreparacion."
                    + "} "
                    + " GROUP BY ?r ?cal ?tiempo ?dif"
                    + " ORDER BY ?cantIng ";
            System.out.println(queryString);
            com.hp.hpl.jena.query.Query q = QueryFactory.create(queryString);
            //execute the query and obtain results
            QueryExecution qe = QueryExecutionFactory.create(q, inf);
            ResultSet results = qe.execSelect();

            //print query results
            while (results.hasNext()) {
                Receta rec2 = new Receta();
                //System.out.println(results.getResourceModel());
                //ResultSetFormatter.out(System.out,results, q);
                QuerySolution qs = results.next();

                Literal caloriasLiteral = qs.getLiteral("cal");
                int calorias = caloriasLiteral.getInt();
                rec2.setCalorias(calorias);

                Literal tiempoLiteral = qs.getLiteral("tiempo");
                int tiempo = tiempoLiteral.getInt();
                rec2.setTiempo(tiempo);

                Literal dificultadLiteral = qs.getLiteral("dif");
                int dificultad = dificultadLiteral.getInt();
                rec2.setDificultad(dificultad);

                rec2.setIngredientes(qs.get("listaIng"));

                rec2.setModoPreparacion(qs.get("Preparacion"));

                Literal cantidadIngredientesLiteral = qs.getLiteral("cantIng");
                int cantidadIngredientes = cantidadIngredientesLiteral.getInt();
                rec2.setCantidadIngredientes(cantidadIngredientes);

                Literal nombreRecetaLiteral = qs.getLiteral("r");
                Object nombreReceta = nombreRecetaLiteral.getString();
                System.out.println("jaaaaaa" + nombreReceta);
                rec2.setNombreReceta(qs.getLiteral("r"));

                list.add(rec2);
                if (list.contains(rec2)) {
                    list.remove(rec2);
                }

                list.add(rec2);

            }

        } catch (java.lang.NullPointerException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Query Failed !");
        }

        list = new ArrayList<>(new LinkedHashSet<>(list));

        return list;
    }

}
