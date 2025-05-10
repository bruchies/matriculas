import { useNavigate } from 'react-router-dom';
import '../index.css';

export default function Menu() {
  const navigate = useNavigate();

  return (
    <div className="container">
      <h1 className="titulo">Menu Principal</h1>
      <p className="texto">Escolha uma funcionalidade:</p>

      <div className="botoes-menu">
        <button className="botao" onClick={() => navigate('/dashboard')}>📊 Dashboard</button>
        <button className="botao" onClick={() => navigate('/grafico-total-ano')}>📈 Total de alunos matriculados por Ano</button>
        <button className="botao" onClick={() => navigate('/grafico-total-ano-modalidade')}>📈 Total de alunos matriculados por Ano e Modalidade</button>
        <button className="botao" onClick={() => navigate('/ranking-estado-modalidade')}>🗺️ Ranking por Estado</button>
        <button className="botao" onClick={() => navigate('/ranking-modalidade')}>🏠 Ranking de Cursos 2022 por Modalidade</button>
        <button className="botao" onClick={() => navigate('/ranking-top-cursos-2022')}>🏅 Top 10 Cursos em 2022</button>
      </div>
    </div>
  );
}
