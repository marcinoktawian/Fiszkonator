-- á
-- Á 
-- é
-- É
-- í
-- Í
-- ñ
-- Ñ
-- ó
-- Ó
-- ú
-- Ú
-- ü
-- Ü 
-- ¿
-- ¡

INSERT INTO Kategoria VALUES(2,"Jak się masz");

INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "być", "(dla określenia stanu emocjonalnego lub położenia)", "ja jestem ty jesteś on/-a; pan/-i jest","estar",null, "yo estoy tú estás él/ella; usted está", "A1", "starter_010");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "Cześć!", "(na powitanie; mniej formalnie)", null, "¡Hola!",null, null, "A1", "starter_011");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "jak?",null ,"Jak się masz", "¿cómo?",null, "¿Cómo estás?", "A1", "starter_012");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "dobrze; w porządku", null, "Masz się dobrze?", "bien",null, "¿Estás bien?", "A1", "starter_013");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "tak", null, "Tak, mam się dobrze.", "sí",null, "Sí, estoy bien.", "A1", "starter_014");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "nie", null, "Nie, nie mam się dobrze", "no",null, "No, no estoy bien", "A1", "starter_015");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "Dziękuję!", null, "Mam się dobrze, dziękuję.", "¡Gracias!",null, "Estoy bien, gracias", "A1", "starter_016");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "i; a", null, "A jak się ma Paweł?", "y",null, "¿Y cómo está Pablo?", "A1", "starter_017");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy,KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (2, 1, "bardzo", null, "Paweł ma się bardzo dobrze." , "muy",null, "Pablo está muy bien", "A1", "starter_018");

INSERT INTO Statystyki VALUES(10,0.0,0.0,1);
INSERT INTO Statystyki VALUES(11,0.0,0.0,1);
INSERT INTO Statystyki VALUES(12,0.0,0.0,1);
INSERT INTO Statystyki VALUES(13,0.0,0.0,1);
INSERT INTO Statystyki VALUES(14,0.0,0.0,1);
INSERT INTO Statystyki VALUES(15,0.0,0.0,1);
INSERT INTO Statystyki VALUES(16,0.0,0.0,1);
INSERT INTO Statystyki VALUES(17,0.0,0.0,1);
INSERT INTO Statystyki VALUES(18,0.0,0.0,1);


INSERT INTO Kategoria VALUES(3,"Skąd jesteś");
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'gdzie?', null, null, '¿dónde?', null,null, 'A1', 'starter_019');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'z', null, 'Skąd jesteś?', 'de',null, '¿De dónde eres?', 'A1', 'starter_020');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'Polska', null, 'Jestem z Polski.', 'Polonia',null,'Soy de Polonia', 'A1', 'starter_021');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'Warszawa', null, '-Jesteś z Warszawy? -Nie, nie jestem z Warszawy.','Varsovia', null, '-¿Eres de Varsovia? -No, no soy de Varsovia.', 'A1', 'starter_022');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'w',null, '-Gdzie jesteś? -Jestem w Polsce.', 'en', null, '-¿Dónde estás? -Estoy en Polonia.', 'A1', 'starter_023');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'Hiszpania', null, '-Czy Piotr jest w Hiszpanii? -Tak, Piotr jest w Hiszpanii.', 'España', null, '-¿Está Pedro en España? -Sí, Pedro está en España.', 'A1', 'starter_024');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'Madryt,', null, 'Czy pan jest z Madrytu?', 'Madrid', null, '¿Es usted de Madrid?', 'A1', 'starter_025');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'być', '(dla określenia toźsamości lub pochodzenia, narodowości, zawodu)', 'my jesteśmy wy jesteście oni/-e; państwo są', 'ser', null, 'nosotros/-as somos vosotros/-as sois ellos/-as; ustedes son', 'A1', 'starter_026');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'być', '(dla określenia stanu emocjonalnego lub położenia)', ' my jesteśmy wy jesteście oni/-e; państwo są', 'estar', null, 'nosotros/-as estamos vosotros/-as estáis ellos/-as; ustedes estan', 'A1', 'starter_027');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'my', '(mężczyźni)', '(My) jesteśmy w Polsce', 'nosotros','Formy nosotros używa się też, mówiąc o mężczyznach i kobietach.' ,'(Nosotros) estamos en Polonia.', 'A1', 'starter_028');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'my', '(kobiety)', '(My) jesteśmy z Polski.', 'nosotras', null ,'(Nosotras) somos de Polonia.', 'A1', 'starter_029');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'wy', '(mężczyźni)', 'Czy (wy) jesteście w Hiszpanii?', 'vosotros', 'Formy vosotros używa się też, mówiąc o mężczyznach i kobietach.' , '¿Estáis (vosotros) en España?', 'A1', 'starter_030');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'wy', '(kobiety)', 'Czy (wy) jesteście z Hiszpanii?', 'vosotras', null, '¿Sois (vosotras) de España?', 'A1', 'starter_031');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'oni', null, '-Czy oni są w Madrycie? -Tak, oni są w Madrycie.', 'ellos', null, '-¿Están (ellos) en Madrid? -Sí, (ellos) están en Madrid.', 'A1', 'starter_032');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'one', null, '-Czy one są z Madrytu? -Nie, one nie są z Madrytu.', 'ellas', null, '-¿Son (ellas) de Madrid? -No, (ellas) no son de Madrid.', 'A1', 'starter_033');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (3, 1, 'państwo', null, 'Czy państwo są z Warszawy?', 'ustedes', null, '¿Son ustedes de Varsovia?', 'A1', 'starter_034');
INSERT INTO Statystyki VALUES(19,0.0,0.0,1);
INSERT INTO Statystyki VALUES(20,0.0,0.0,1);
INSERT INTO Statystyki VALUES(21,0.0,0.0,1);
INSERT INTO Statystyki VALUES(22,0.0,0.0,1);
INSERT INTO Statystyki VALUES(23,0.0,0.0,1);
INSERT INTO Statystyki VALUES(24,0.0,0.0,1);
INSERT INTO Statystyki VALUES(25,0.0,0.0,1);
INSERT INTO Statystyki VALUES(26,0.0,0.0,1);
INSERT INTO Statystyki VALUES(27,0.0,0.0,1);
INSERT INTO Statystyki VALUES(28,0.0,0.0,1);
INSERT INTO Statystyki VALUES(29,0.0,0.0,1);
INSERT INTO Statystyki VALUES(30,0.0,0.0,1);
INSERT INTO Statystyki VALUES(31,0.0,0.0,1);
INSERT INTO Statystyki VALUES(32,0.0,0.0,1);
INSERT INTO Statystyki VALUES(33,0.0,0.0,1);
INSERT INTO Statystyki VALUES(34,0.0,0.0,1);

INSERT INTO Kategoria VALUES(4,"Mówisz po hiszpańsku");

INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'nazywać się', null, 'ja nazywam się ty nazywasz się on/-a; pan/-i nazywa się', 'llamarse','Zaimek zwrotny "Się" ("se") odmienia się przez osoby', 'yo me llamo tú te llamas él/ella; usted se llama', 'A1', 'starter_035');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Anglia', null, '-Jak się nazywasz? -Nazywam się Anna i jestem z Anglii.', 'Inglaterra', 'W zdaniach twierdzących zaimek "się" ("se") stawia się przed czasownikiem', '¿Cómo te llamas? -Me llamo Anna y soy de Inglaterra.', 'A1', 'starter_036');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Niemcy', null, '-Jak on się nazywa? -On nazywa się Uwe i jest z Niemiec.', 'Alemania', 'Tworząc pytanie; podmiot stawia się na końcu.', '-¿Cómo se llama él? -Él se llama Uwe y es de Alemania', 'A1', 'starter_037');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Francja', null, '-Jak ona się nazywa? -Ona nazywa się Anita i jest z Francji.', 'Francia', null, '-¿Cómo se llama ella? -Ella se llama Anita y es de Francia', 'A1', 'starter_038');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Włochy', null, '-Jak się pani nazywa i skąd pani jest? -Nazywam się Sara i jestem z Włoch.', 'Italia', null, '-¿Cómo se llama y de dónde es usted? -Me llamo Sara y soy de Italia.', 'A1', 'starter_039');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Rosja', null, '-Jak się pan nazywa i skąd pan jest? -Nazywam się Borys i jestem z Rosji.', 'Rusia', null, '-¿Cómo se llama y de dónde es usted? -Me llamo Boris y soy de Rusia.', 'A1', 'starter_040');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'wiedzieć', null, 'ja wiem ty wiesz on/-a; pan/-i wie my wiemy wy wiecie oni/-e; państwo wiedzą', 'saber', null, 'yo sé tú sabes él/ella; usted sabe nosotros/-as sabemos vosotros/-as sabéis ellos/-as; ustedes saben', 'A1', 'starter_041');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'to', '(odnosi się do rzeczy nienazwanych i pojęć abstrakcyjnych)', 'Co to jest?', 'esto', null, '¿Qué es esto?', 'A1', 'starter_042');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'tamto', '(odnosi się do rzeczy nienazwanych i pojęć abstrakcyjnych)', 'Co to jest (tamto)?', 'aquello',null, '¿Qué es aquello?', 'A1', 'starter_043');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język hiszpański', null, '-Jak to się nazywa po hiszpańsku? -Nie wiem (tego)', 'el español', 'W konstrukcjach z nazwą języka zwykle pomija sie rodzajnik', '-¿Cómo se llama esto en español? -No lo sé.', 'A1', 'starter_044');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'rozumieć', null, 'ja rozumiem ty rozumiesz on/-a; pan/-i rozumie my rozumiemy wy rozumiecie oni/-e; państwo rozumieją', 'entender', null, 'yo entiendo tú entiendes él/ella; usted entiende nosotros/-as entendemos vosotros/-as entendéis ellos/-as; ustedes entienden', 'A1', 'starter_045');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'Przykro mi!', '(Przepraszam!)', 'Przykro mi, nie rozumiem hiszpańskiego.', '¡Lo siento!', null, 'Lo siento, no entiendo español.', 'A1', 'starter_046');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'wszystko', null, 'Rozumiem wszystko', 'todo', null, 'Entiendo todo', 'A1', 'starter_047');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'nic', null, 'Nic nie rozumiem.', 'nada', null, 'No entiendo nada.', 'A1', 'starter_048');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'mówić', null, 'ja mówię ty mówisz on/-a; pan/-i mówi my mówimy wy mówicie oni/-e; państwo mówią', 'hablar', null, 'yo hablo tú hablas él/ella; usted habla nosotros/-as hablamos vosotros/-as habláis ellos/-as; ustedes hablan', 'A1', 'starter_049');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język angielski', null, '-Mówisz po angielsku? -Tak, mówię po angielsku.', 'el inglés', null, '-¿Hablas inglés? -Sí, hablo inglés.', 'A1', 'starter_050');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język niemiecki', null, 'Mówię dobrze po niemiecku.', 'el alemán', null, 'Hablo bien alemán.', 'A1', 'starter_051');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język francuski',null, '-Mówi pan po francusku? -Tak, mówię po francusku.', 'el francés', null, '-¿Habla usted francés? -Sí, hablo francés.', 'A1', 'starter_052');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język włoski', null, '-Mówicie po włosku? Nie, nie mówimy po włosku.', 'el italiano', null, '-¿Habláis italiano? -No, no hablamos italiano', 'A1', 'starter_053');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, KomentarzPolski,UzyciePolski, Obcy, KomentarzObcy, UzycieObcy,Poziom,Nagranie) VALUES (4, 1, 'język rosyjski', null, 'Oni mówią dobrze po rosyjsku.', 'el ruso', null, 'Ellos hablan bien ruso.', 'A1', 'starter_054');
INSERT INTO Statystyki VALUES(35,0.0,0.0,1);
INSERT INTO Statystyki VALUES(36,0.0,0.0,1);
INSERT INTO Statystyki VALUES(37,0.0,0.0,1);
INSERT INTO Statystyki VALUES(38,0.0,0.0,1);
INSERT INTO Statystyki VALUES(39,0.0,0.0,1);
INSERT INTO Statystyki VALUES(40,0.0,0.0,1);
INSERT INTO Statystyki VALUES(41,0.0,0.0,1);
INSERT INTO Statystyki VALUES(42,0.0,0.0,1);
INSERT INTO Statystyki VALUES(43,0.0,0.0,1);
INSERT INTO Statystyki VALUES(44,0.0,0.0,1);
INSERT INTO Statystyki VALUES(45,0.0,0.0,1);
INSERT INTO Statystyki VALUES(46,0.0,0.0,1);
INSERT INTO Statystyki VALUES(47,0.0,0.0,1);
INSERT INTO Statystyki VALUES(48,0.0,0.0,1);
INSERT INTO Statystyki VALUES(49,0.0,0.0,1);
INSERT INTO Statystyki VALUES(50,0.0,0.0,1);
INSERT INTO Statystyki VALUES(51,0.0,0.0,1);
INSERT INTO Statystyki VALUES(52,0.0,0.0,1);
INSERT INTO Statystyki VALUES(53,0.0,0.0,1);
INSERT INTO Statystyki VALUES(54,0.0,0.0,1);

--
--
--
-- EL PUERTO
--
--
--

INSERT INTO Zestaw VALUES(2,"El Puerto");
INSERT INTO Kategoria VALUES(5,"Praca-słówka");

INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'pocić się' , 'sudar', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'czuć niepokój' , 'sentir ansiedad', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'śnić, marzyć o czymś' , 'soñar con algo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'góra papierów, dokumentów' , 'la montaña de papeles', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'stawić czemuś czoła' , 'afrontar algo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'moim zdaniem' , 'en mi opinión', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'reguły księgowości' , 'las reglas de contabilidad', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'nadmiar, przedawkowanie' , 'la sobredosis', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'starać się nie myśleć' , 'tratar de no pensar', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'pracować (tymczasowo) jako' , 'trabajar de', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'przepis, sposób' , 'la receta', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'stres pierwszego dnia pracy' , 'el estrés del primer día de trabajo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'bardzo się zdenerwować' , 'ponerse muy nervioso', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'starać się nie myśleć' , 'intentar no pensar', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'stresować się' , 'estresarse', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'zazwyczaj coś robić' , 'soler hacer algo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'wyzwanie' , 'el reto', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'szansa' , 'la oportunidad', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'szczęście' , 'la suerte', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (5, 2, 'Powodzenia!' , '¡Suerte!', 'A2.1');
INSERT INTO Statystyki VALUES(55,0.0,0.0,1);
INSERT INTO Statystyki VALUES(56,0.0,0.0,1);
INSERT INTO Statystyki VALUES(57,0.0,0.0,1);
INSERT INTO Statystyki VALUES(58,0.0,0.0,1);
INSERT INTO Statystyki VALUES(59,0.0,0.0,1);
INSERT INTO Statystyki VALUES(60,0.0,0.0,1);
INSERT INTO Statystyki VALUES(61,0.0,0.0,1);
INSERT INTO Statystyki VALUES(62,0.0,0.0,1);
INSERT INTO Statystyki VALUES(63,0.0,0.0,1);
INSERT INTO Statystyki VALUES(64,0.0,0.0,1);
INSERT INTO Statystyki VALUES(65,0.0,0.0,1);
INSERT INTO Statystyki VALUES(66,0.0,0.0,1);
INSERT INTO Statystyki VALUES(67,0.0,0.0,1);
INSERT INTO Statystyki VALUES(68,0.0,0.0,1);
INSERT INTO Statystyki VALUES(69,0.0,0.0,1);
INSERT INTO Statystyki VALUES(70,0.0,0.0,1);
INSERT INTO Statystyki VALUES(71,0.0,0.0,1);
INSERT INTO Statystyki VALUES(72,0.0,0.0,1);
INSERT INTO Statystyki VALUES(73,0.0,0.0,1);
INSERT INTO Statystyki VALUES(74,0.0,0.0,1);

INSERT INTO Kategoria VALUES(6,"Zawody");

INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pracownik' , 'el trabajador', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'menedżer, kierownik' , 'el gerente', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'dyrektor' , 'el director', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'sekretarka' , 'la secretaria', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'księgowy' , 'el contable', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'funkcjonariusz, urzędnik' , 'el funcionario', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'informatyk' , 'el informático', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pracownik administracyjny' , 'el administrador', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'makler giełdowy' , 'el corredor de bolsa', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'przedstawiciel handlowy' , 'el representante comercial', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'agent reklamowy' , 'el agente publicitario', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'handlowiec' , 'el comerciante', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'prawnik' , 'el abogado', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'adwokat' , 'el abogado defensor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'prokurator' , 'el fiscal', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'sędzia' , 'el juez', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'aktor' , 'el actor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'malarz' , 'el pintor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'piosenkarz, śpiewak' , 'el cantante', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'muzyk' , 'el músico', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'fotograf' , 'el fotógrafo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'modelka' , 'la modelo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'projektant' , 'el diseñador', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'architekt' , 'el arquitecto', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'tłumacz ustny' , 'el intérprete', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'tłumacz' , 'el traductor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pisarz' , 'el escritor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'redaktor' , 'el editor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'dziennikarz' , 'el periodista', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'lekarz' , 'el médico', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pielęgniarka' , 'la enfermera', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'dentysta' , 'el dentista', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'policjant' , 'el policía', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'strażak' , 'el bombero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'weterynarz' , 'el veterinario', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'kucharz' , 'el cocinero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'kelner' , 'el camarero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'sprzedawca' , 'el dependiente', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'listonosz' , 'el cartero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pomoc domowa' , 'el empleado de hogar', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'fryzjer' , 'el peluquero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'hudraulik' , 'el fontanero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'elektryk' , 'el electricista', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'mechanik' , 'el mecánico', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'Inżynier' , 'el ingeniero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'rolnik' , 'el agricultor', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'pracownik samodzielny, freelancer' , 'el autónomo', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'robotnik' , 'el obrero', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (6, 2, 'murarz, robotnik' , 'el albañil', 'A2.1');
INSERT INTO Statystyki VALUES(75,0.0,0.0,1);
INSERT INTO Statystyki VALUES(76,0.0,0.0,1);
INSERT INTO Statystyki VALUES(77,0.0,0.0,1);
INSERT INTO Statystyki VALUES(78,0.0,0.0,1);
INSERT INTO Statystyki VALUES(79,0.0,0.0,1);
INSERT INTO Statystyki VALUES(80,0.0,0.0,1);
INSERT INTO Statystyki VALUES(81,0.0,0.0,1);
INSERT INTO Statystyki VALUES(82,0.0,0.0,1);
INSERT INTO Statystyki VALUES(83,0.0,0.0,1);
INSERT INTO Statystyki VALUES(84,0.0,0.0,1);
INSERT INTO Statystyki VALUES(85,0.0,0.0,1);
INSERT INTO Statystyki VALUES(86,0.0,0.0,1);
INSERT INTO Statystyki VALUES(87,0.0,0.0,1);
INSERT INTO Statystyki VALUES(88,0.0,0.0,1);
INSERT INTO Statystyki VALUES(89,0.0,0.0,1);
INSERT INTO Statystyki VALUES(90,0.0,0.0,1);
INSERT INTO Statystyki VALUES(91,0.0,0.0,1);
INSERT INTO Statystyki VALUES(92,0.0,0.0,1);
INSERT INTO Statystyki VALUES(93,0.0,0.0,1);
INSERT INTO Statystyki VALUES(94,0.0,0.0,1);
INSERT INTO Statystyki VALUES(95,0.0,0.0,1);
INSERT INTO Statystyki VALUES(96,0.0,0.0,1);
INSERT INTO Statystyki VALUES(97,0.0,0.0,1);
INSERT INTO Statystyki VALUES(98,0.0,0.0,1);
INSERT INTO Statystyki VALUES(99,0.0,0.0,1);
INSERT INTO Statystyki VALUES(100,0.0,0.0,1);
INSERT INTO Statystyki VALUES(101,0.0,0.0,1);
INSERT INTO Statystyki VALUES(102,0.0,0.0,1);
INSERT INTO Statystyki VALUES(103,0.0,0.0,1);
INSERT INTO Statystyki VALUES(104,0.0,0.0,1);
INSERT INTO Statystyki VALUES(105,0.0,0.0,1);
INSERT INTO Statystyki VALUES(106,0.0,0.0,1);
INSERT INTO Statystyki VALUES(107,0.0,0.0,1);
INSERT INTO Statystyki VALUES(108,0.0,0.0,1);
INSERT INTO Statystyki VALUES(109,0.0,0.0,1);
INSERT INTO Statystyki VALUES(110,0.0,0.0,1);
INSERT INTO Statystyki VALUES(111,0.0,0.0,1);
INSERT INTO Statystyki VALUES(112,0.0,0.0,1);
INSERT INTO Statystyki VALUES(113,0.0,0.0,1);
INSERT INTO Statystyki VALUES(114,0.0,0.0,1);
INSERT INTO Statystyki VALUES(115,0.0,0.0,1);
INSERT INTO Statystyki VALUES(116,0.0,0.0,1);
INSERT INTO Statystyki VALUES(117,0.0,0.0,1);
INSERT INTO Statystyki VALUES(118,0.0,0.0,1);
INSERT INTO Statystyki VALUES(119,0.0,0.0,1);
INSERT INTO Statystyki VALUES(120,0.0,0.0,1);
INSERT INTO Statystyki VALUES(121,0.0,0.0,1);
INSERT INTO Statystyki VALUES(122,0.0,0.0,1);
INSERT INTO Statystyki VALUES(123,0.0,0.0,1);

INSERT INTO Kategoria VALUES(7,"Miejsce pracy");

INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'biuro' , 'la oficina', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'gabinet' , 'el despacho', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'administracja publiczna' , 'la administración pública', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'urząd miasta' , 'el ayuntamiento', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'remiza strażacka' , 'el parque de bomberos', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'posterunek policji' , 'la comisaría de policía', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'szkoła' , 'la escuela', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'uniwersytet' , 'la universidad', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'kuchnia' , 'la cocina', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'restauracja' , 'el restaurante', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'bar' , 'el bar', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'sklep' , 'la tienda', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'targ' , 'el mercado', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'biuro architektoniczne' , 'el estudio de arquitectura', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'studio fotograficzne' , 'el estudio de fotografía', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'małe przedsiębiorstwo' , 'la empresa pequeña', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'średnie przedsiębiorstwo' , 'la empresa mediana', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'firma międzynarodowa' , 'la empresa multinacional', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'firma budowlana' , 'la empresa constructora', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'biuro nieruchomości' , 'la inmobiliaria', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'warsztat' , 'el taller', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'budowa' , 'la obra', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'centrum medyczne' , 'el centro médico', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'sąd' , 'el juzgado', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'drukamia' , 'la imprenta', 'A2.1');
INSERT INTO Fiszka (IdKategorii, IdZestawu, Polski, Obcy, Poziom) VALUES (7, 2, 'wydawnictwo' , 'la editorial', 'A2.1');
INSERT INTO Statystyki VALUES(124,0.0,0.0,1);
INSERT INTO Statystyki VALUES(125,0.0,0.0,1);
INSERT INTO Statystyki VALUES(126,0.0,0.0,1);
INSERT INTO Statystyki VALUES(127,0.0,0.0,1);
INSERT INTO Statystyki VALUES(128,0.0,0.0,1);
INSERT INTO Statystyki VALUES(129,0.0,0.0,1);
INSERT INTO Statystyki VALUES(130,0.0,0.0,1);
INSERT INTO Statystyki VALUES(131,0.0,0.0,1);
INSERT INTO Statystyki VALUES(132,0.0,0.0,1);
INSERT INTO Statystyki VALUES(133,0.0,0.0,1);
INSERT INTO Statystyki VALUES(134,0.0,0.0,1);
INSERT INTO Statystyki VALUES(135,0.0,0.0,1);
INSERT INTO Statystyki VALUES(136,0.0,0.0,1);
INSERT INTO Statystyki VALUES(137,0.0,0.0,1);
INSERT INTO Statystyki VALUES(138,0.0,0.0,1);
INSERT INTO Statystyki VALUES(139,0.0,0.0,1);
INSERT INTO Statystyki VALUES(140,0.0,0.0,1);
INSERT INTO Statystyki VALUES(141,0.0,0.0,1);
INSERT INTO Statystyki VALUES(142,0.0,0.0,1);
INSERT INTO Statystyki VALUES(143,0.0,0.0,1);
INSERT INTO Statystyki VALUES(144,0.0,0.0,1);
INSERT INTO Statystyki VALUES(145,0.0,0.0,1);
INSERT INTO Statystyki VALUES(146,0.0,0.0,1);
INSERT INTO Statystyki VALUES(147,0.0,0.0,1);
INSERT INTO Statystyki VALUES(148,0.0,0.0,1);
INSERT INTO Statystyki VALUES(149,0.0,0.0,1);

COMMIT;