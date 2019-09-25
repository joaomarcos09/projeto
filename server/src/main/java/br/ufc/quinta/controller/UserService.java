package br.ufc.quinta.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import br.ufc.quinta.model.User;
import br.ufc.quinta.repository.UserRepository;
//import br.ufc.quinta.util.Upload;

@RestController
@RequestMapping(path = "/api/users")
@CrossOrigin
public class UserService {
	@Autowired
	private UserRepository users;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(users.findAll(), HttpStatus.OK);
		// return new ResponseEntity<List<Curso>>(cursos.findAll(new
		// Sort(Sort.Direction.ASC, "id")), HttpStatus.OK);
	}

	@RequestMapping(value = "{matricula}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("matricula") Integer matricula) {
		Optional<User> user = users.findById(matricula);

		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@RequestParam("nome") String nome) {
		System.out.println(nome);
		User user = users.findByNome(nome);

		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<User> addUser(String nome, String email, String curso, String matricula, String senha) {
		if (nome == null || email == null || nome.equals("null") || email.equals("null") || matricula == null
				|| senha == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		User user = new User(matricula, nome, email, curso, senha);
		User userAux = users.save(user);
		// try {
		// Upload.uploadFileServer(userAux.getMatricula());
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	//@RequestMapping(value = "{matricula}", method = RequestMethod.PUT)
	//public ResponseEntity<User> atualizarUser(String matricula, String nome, String email,
	//		String curso, String senha) {
	//	if (nome == null || email == null || nome.equals("null") || email.equals("null") || curso == null
//				|| curso.equals("null") || senha == null || senha.equals("null")) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		//Optional<User> user = users.findById(id);

	//	if (user.isPresent()) {
	//		user.get().setNome(matricula);
	//		user.get().setNome(nome);
	//		user.get().setEmail(email);
	//		user.get().setNome(curso);
	//		user.get().setEmail(senha);
//			users.save(user.get());
			//try {
		//		if (image != null) {
		//			Upload.uploadFileServer(image.getInputStream(), id);
		//		}
		//	} catch (IOException e) {
		//		e.printStackTrace();
		//	}

	//		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	//	} else {
	//		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	//	}
//	}

	//@RequestMapping(value = "{matricula}", method = RequestMethod.DELETE)
	//public ResponseEntity<?> deletarUser(@PathVariable("matricula") String matricula) {
		//if (users.existsById(matricula)) {
			//users.deleteById(matricula);
		//	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		//} else {
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
	//}
}
