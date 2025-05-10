import GraficoTopCursos2022 from '../components/GraficoTopCursos2022';
import { useNavigate } from 'react-router-dom';
import '../index.css';

export default function TopCursos2022() {
  const navigate = useNavigate();

  return (
    <div className="container">
      <h2 className="titulo">Top 10 Cursos com Mais Matrículas em 2022</h2>
      <button className="botao-voltar" onClick={() => navigate('/')} style={{ marginBottom: '20px' }}>
        🔙 Voltar ao Menu
      </button>
      <GraficoTopCursos2022 />
    </div>
  );
}
