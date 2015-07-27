require_relative 'setup'

describe 'SecurityHelper' do

  it 'registers signatures with SecurityHelper' do
    skip( 'jruby too old' ) if too_old
    # clear the fast engines
    engines.clear
    # setup the fast engines
    load( "${this}/../lib/fast-rsa-engine.rb" )

    expect( engines.size ).to eq( 12 )
  end
  
end
