package com.ESFE.Asistencias.Controladores;

import com.ESFE.Asistencias.Entidades.Estudiante;
import com.ESFE.Asistencias.Entidades.EstudianteGrupo;
import com.ESFE.Asistencias.Entidades.Grupo;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteGrupoServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IEstudianteServices;
import com.ESFE.Asistencias.Servicios.Interfaces.IGrupoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/estudianteGrupos")
public class EstudianteGrupoController {

    @Autowired
    private IEstudianteGrupoServices estudianteGrupoServices;

    @Autowired
    private IEstudianteServices estudianteServices;

    @Autowired
    private IGrupoServices grupoServices;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1) - 1;
        int pageSize = size.orElse(5);
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<EstudianteGrupo> estudiantes = estudianteGrupoServices.BuscarTodosPaginados(pageable);
        model.addAttribute("estudianteGrupos", estudiantes);

        int totalPage = estudiantes.getTotalPages();
        if (totalPage > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
                    .boxed()
                    .collect(Collectors.toList());

            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "estudianteGrupo/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("estudianteGrupo", new EstudianteGrupo());
        model.addAttribute("estudiantes", estudianteServices.ObtenerTodos());
        model.addAttribute("grupos", grupoServices.ObtenerTodos());
        return "estudianteGrupo/create";
    }

    @PostMapping("/save")
    public String save(@RequestParam Integer estudianteId,
                       @RequestParam Integer grupoId,
                       RedirectAttributes attributes) {
        Estudiante estudiante = estudianteServices.BuscarPorId(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + estudianteId));
        Grupo grupo = grupoServices.BuscarPorId(grupoId)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado: " + grupoId));

        EstudianteGrupo estudianteGrupo = new EstudianteGrupo();
        estudianteGrupo.setEstudiante(estudiante);
        estudianteGrupo.setGrupo(grupo);
        estudianteGrupoServices.CrearOeditar(estudianteGrupo);
        attributes.addFlashAttribute("msg", "Estudiante grupo creado.");

        return "redirect:/estudianteGrupos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante-Grupo no encontrado: " + id));
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante-Grupo no encontrado: " + id));
        model.addAttribute("estudiantes", estudianteServices.ObtenerTodos());
        model.addAttribute("grupos", grupoServices.ObtenerTodos());
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id,
                         @RequestParam Integer estudianteId,
                         @RequestParam Integer grupoId,
                         RedirectAttributes attributes) {

        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante-Grupo no encontrado: " + id));

        Estudiante estudiante = estudianteServices.BuscarPorId(estudianteId)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado: " + estudianteId));
        Grupo grupo = grupoServices.BuscarPorId(grupoId)
                .orElseThrow(() -> new IllegalArgumentException("Grupo no encontrado: " + grupoId));

        estudianteGrupo.setEstudiante(estudiante);
        estudianteGrupo.setGrupo(grupo);

        estudianteGrupoServices.CrearOeditar(estudianteGrupo);
        attributes.addFlashAttribute("msg", "Asignación modificada correctamente.");

        return "redirect:/estudianteGrupos";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model) {
        EstudianteGrupo estudianteGrupo = estudianteGrupoServices.BuscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante-Grupo no encontrado: " + id));
        model.addAttribute("estudianteGrupo", estudianteGrupo);
        return "estudianteGrupo/delete";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Integer id, RedirectAttributes attributes) {
        estudianteGrupoServices.EliminarPorId(id);
        attributes.addFlashAttribute("msg", "Asignacion eliminada correctamente.");
        return "redirect:/estudianteGrupos";
    }
}
